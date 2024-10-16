package com.TicketManagement.TrainTicket.service;

import com.TicketManagement.TrainTicket.dto.UserDTO;
import com.TicketManagement.TrainTicket.entity.User;
import com.TicketManagement.TrainTicket.exception.*;
import com.TicketManagement.TrainTicket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final JWTService jwtService;
    private final BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();
    private final AuthenticationManager authenticationManager;
    private final ApplicationContext context;

    public List<UserDTO> getUserDetails() {
        List<UserDTO> userList = new ArrayList<>();
        this.userRepo.findAll().forEach(user -> {
            if (user.getStatus()) {
                userList.add(new UserDTO(user.getUserId(), user.getUserName(), user.getAddress(), user.getPhoneNumber(), user.getStatus()));
            }
        });
        if (userList.isEmpty()) {
            throw new NoDataFoundException("No Data Found");
        }
        return userList;
    }

    public void saveUser(final User user) {
        getUserDetails();
        user.setPassword(encrypt.encode(user.getPassword()));
        this.userRepo.save(user);
    }

    public UserDTO getUserById(final Long id) {
        if (id==null) {
            throw new InputMantatoryException("No Id Given");
        }
        User user = this.userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        if (user.getStatus())
            return new UserDTO(user.getUserId(), user.getUserName(), user.getAddress(), user.getPhoneNumber(), user.getStatus());
        else
            throw new NotAnActiveUserException("Not an Active User");

    }

    public List<UserDTO> getAllUsers() {
        return new ArrayList<>(getUserDetails());
    }

    public String deleteUser(final Long userId) {
        final String[] str = {""};
        str[0] = "No Id Matched";
        getUserDetails().forEach(user -> {
            if (user.getUserId().equals(userId)) {
                user.setStatus(false);
                User userDTO = new User();
                userDTO.setUserId(user.getUserId());
                userDTO.setStatus(user.getStatus());
                userDTO.setUserName(user.getUserName());
                userDTO.setAddress(user.getAddress());
                saveUser(userDTO);
                str[0] = "Deleted";
            } else {
                throw new NoIdMatchedException("No ID Matched");
            }
        });
        return str[0];
    }


    public String verifyUser(final User user) {
        try {
            User userTemp = userRepo.getByUserName(user.getUserName());
            if (userTemp == null) {
                throw new InvalidUserException("Invalid User");
            }
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (!passwordEncoder.matches(user.getPassword(), userTemp.getPassword())) {
                throw new PasswordNotMatchException("Password Not Matched");
            }
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            if (authentication.isAuthenticated()) {
                if (userRepo.getByUserName(user.getUserName()).getStatus())
                    return jwtService.generateToken(user.getUserName());
                else {
                    throw new NotAnActiveUserException("Not an Active User");
                }
            } else {
                throw new NotAuthenticatedException("Not Authenticated");
            }
        } catch (InvalidUserException ex) {
            throw new InvalidUserException("Invalid User");
        } catch (NotAnActiveUserException ex) {
            throw new NotAnActiveUserException("Not an Active User");
        } catch (PasswordNotMatchException ex) {
            throw new PasswordNotMatchException("Password Not Matched");
        } catch (Exception ex) {
            throw new NotAuthenticatedException("Failed to authenticate the user");
        }
    }
}

