package com.TicketManagement.TrainTicket.service;

import com.TicketManagement.TrainTicket.config.UserPrincipal;
import com.TicketManagement.TrainTicket.dto.UserDTO;
import com.TicketManagement.TrainTicket.entity.User;
import com.TicketManagement.TrainTicket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepo;
    private BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();

    public List<UserDTO> getUserDetails() {
        List<UserDTO> userList = new ArrayList<>();
        this.userRepo.findAll().forEach(user -> {
            if (user.getStatus()) {
                userList.add(new UserDTO(user.getUserId(), user.getName(), user.getAddress(), user.getPhoneNumber(), user.getStatus()));
            }
        });
        return userList;
    }

    public void saveUser(final User user) {
        getUserDetails();
        user.setPassword(encrypt.encode(user.getPassword()));
        this.userRepo.save(user);
    }

    public UserDTO getUserById(final Long id) {
        User user= this.userRepo.findById(id).orElseThrow(()->new RuntimeException("Not Found"));
        return new UserDTO(user.getUserId(),user.getName(),user.getAddress(),user.getPhoneNumber(),user.getStatus());
    }

    public List<UserDTO> getAllUsers() {
        return new ArrayList<>(getUserDetails());
    }

    public String deleteUser(Long userId) {
        final String[] str = {""};
        str[0]="No Id Matched";
        getUserDetails().forEach(user -> {
            if (user.getUserId().equals(userId)) {
                user.setStatus(false);
                User userDTO = new User();
                userDTO.setUserId(user.getUserId());
                userDTO.setStatus(user.getStatus());
                userDTO.setName(user.getName());
                userDTO.setAddress(user.getAddress());
                saveUser(userDTO);
                str[0] = "Deleted";
            }
        });
        return str[0];
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("login")
                        .permitAll()
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.getByName(username);

        if (user == null) {
            System.out.println("User not found");
        }

        return new UserPrincipal(user);
    }


}

