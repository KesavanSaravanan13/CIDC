package com.TicketManagement.TrainTicket.service;

import com.TicketManagement.TrainTicket.dto.UserDTO;
import com.TicketManagement.TrainTicket.entity.User;
import com.TicketManagement.TrainTicket.exception.*;
import com.TicketManagement.TrainTicket.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private UserService userService;

    private User USER_01 = new User(1L,
            "$2a$10$JMoPpfqLU/n9HXyUQqZggubQ2vEWDuQog5ndieuiFECCosOI3YMTG",
            "Charlie", "788 Oak St, Shelbyville", "556-8765", true,"");

    private User USER_02 = new User(2L,
            "$2a$10$WpC8r7R0K2oTHeyOLk4Vl.p62IqroB8up2k1izZTyBz0Kg7g9Bac.",
            "John Doe", "123 Main St, Springfield", "555-1234", true,"");

    private User USER_03 = new User(3L,
            "$2a$10$HLklNnqP6vlZs7QwTPxb/eo0Q4miZDg2WAbDIQCLBZ6jiy8EtKDHi",
            "Jane Doe", "456 Oak St, Springfield", "555-5678", false,"");

    private User USER_04 = new User(4L,
            "$2a$10$Tnrsuq6qTb8fwMtDodheP.TDMQWgDGZKjv9v1SRCxVJPapYKwehdm",

            "Alice", "789 Elm St, Springfield", "555-9012", true,"");

    private List<UserDTO> userDTOList = new ArrayList<>(Arrays.asList(
            new UserDTO(1L, "Charlie", "788 Oak St, Shelbyville", "556-8765", true,""),
            new UserDTO(2L, "John Doe", "123 Main St, Springfield", "555-1234", true,""),
            new UserDTO(3L, "Jane Doe", "456 Oak St, Springfield", "555-5678", false,""),
            new UserDTO(4L, "Alice", "789 Elm St, Springfield", "555-9012", true,"")
    ));

    @Test
    public void getAllUserUT() {

        List<User> userDTOList = new ArrayList<>(Arrays.asList(
                USER_01,
                USER_02,
                USER_03,
                USER_04
        ));

        when(this.userRepository.findAll()).thenReturn(userDTOList);

        List<UserDTO> list = this.userService.getAllUsers();

        assertThat(list).isNotNull();
        assertThat("Charlie").isEqualTo(list.get(0).getUserName());
    }

    @Test
    public void getAllUsers_ThrowsNoDataFoundException_WhenNoActiveUsersExist() {
        User user = this.USER_03;

        List<User> list = Arrays.asList(user);

        when(this.userRepository.findAll()).thenReturn(list);

        NoDataFoundException exception = assertThrows(NoDataFoundException.class, () -> this.userService.getAllUsers());
        assertEquals("No Data Found", exception.getMessage());
    }

    @Test
    public void getAllUsers_ThrowsNoDataFoundException_WhenNoUsersExist() {
        when(this.userRepository.findAll()).thenReturn(new ArrayList<>());

        NoDataFoundException exception = assertThrows(NoDataFoundException.class, () -> this.userService.getAllUsers());
        assertEquals("No Data Found", exception.getMessage());
    }

    @Test
    public void saveUserUT() {
        User user = this.USER_03;

        when(this.userRepository.save(any(User.class))).thenReturn(user);

        this.userService.saveUser(user);
        verify(this.userRepository, times(1)).save(user);
    }


    @Test
    public void getUserDetailsUT() {
        User user = this.USER_04;
        when(this.userRepository.save(any(User.class))).thenReturn(user); // Mock the save method
        when(this.userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));

        this.userService.saveUser(user);

        assertEquals(this.USER_04, this.userRepository.findById(this.USER_04.getUserId()).orElse(null));
        verify(this.userRepository, times(1)).save(user);

        boolean val = user.getStatus();
        assertTrue(val);

    }

    @Test
    public void getUserDetailsUT_throwsException() {
        when(this.userRepository.findAll()).thenReturn(Collections.emptyList());
        NoDataFoundException exception = assertThrows(NoDataFoundException.class, () -> this.userService.getUserDetails());

        assertThat(exception.getMessage()).isEqualTo("No Data Found");
    }

    @Test
    public void getUserByIdUT() {
        Long user_id = 4L;
        when(this.userRepository.findById(user_id)).thenReturn(Optional.ofNullable(this.USER_04));
        UserDTO user = this.userService.getUserById(user_id);
        assertThat(user.getUserId()).isEqualTo(this.userDTOList.get(3).getUserId());
        assertThat(user.getUserName()).isEqualTo(this.userDTOList.get(3).getUserName());
    }

    @Test
    public void getUserByIdUT_throwsException() {
        Long user_id = 4L;
        when(this.userRepository.findById(user_id)).thenReturn(Optional.empty());
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> this.userService.getUserById(user_id));

        assertThat(exception.getMessage()).isEqualTo("User Not Found");
    }

    @Test
    public void deleteUser_NullUserId_ThrowsNoDataFoundException() {
        Exception exception = assertThrows(NoDataFoundException.class, () -> this.userService.deleteUser(null));
        assertEquals("No Data Found", exception.getMessage());
    }


    @Test
    public void deleteUser_UserNotFound_ThrowsNoIdMatchedException() {
        Long userId = 1L;
        User user = new User();
        user.setUserId(2L);
        user.setStatus(true);

        when(this.userRepository.findAll()).thenReturn(Arrays.asList(user));

        Exception exception = assertThrows(NoIdMatchedException.class, () -> this.userService.deleteUser(userId));
        assertEquals("No ID Matched", exception.getMessage());
    }

    @Test
    public void deleteUserUT() {
        Long userId = 2L;
        User user = new User();
        user.setUserId(2L);
        user.setPassword("john@123");

        when(this.userRepository.findAll()).thenReturn(List.of(user));

        String msg = this.userService.deleteUser(userId);
        assertEquals("Deleted", msg);
        verify(this.userRepository).save(any(User.class));
    }

    @Test
    public void verifyUserUT_Throws_InvalidUserException() {
        User user = new User();
        when(this.userRepository.getByUserName(this.USER_04.getUserName())).thenReturn(any(User.class));

        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> this.userService.verifyUser(this.USER_04));
        assertEquals("Invalid User", exception.getMessage());
    }

    @Test
    public void verifyUserUT_Throws_PasswordNotMatchException() {
        User user = new User();
        user.setUserName("Alice");
        user.setPassword("ali@123");
        when(this.userRepository.getByUserName(this.USER_04.getUserName())).thenReturn(user);

        PasswordNotMatchException exception = assertThrows(PasswordNotMatchException.class, () -> this.userService.verifyUser(user));
        assertEquals("Password Not Matched", exception.getMessage());
    }

    @Test
    public void verifyUserUT_Throws_NotAnActiveUserException() {
        User user = new User();
        user.setUserName("Alice");
        user.setPassword("alice@123");
        user.setStatus(false);

        String encodedPassword = new BCryptPasswordEncoder().encode("alice@123");

        User userTemp = new User();
        userTemp.setUserName("Alice");
        userTemp.setPassword(encodedPassword);
        userTemp.setStatus(false);

        when(this.userRepository.getByUserName("Alice")).thenReturn(userTemp);

        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(this.authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        NotAnActiveUserException exception = assertThrows(NotAnActiveUserException.class, () -> this.userService.verifyUser(user));
        assertEquals("Not an Active User", exception.getMessage());
    }

    @Test
    public void verifyUserUT_Throws_NotAuthenticatedException() {
        User user = new User();
        user.setUserName("Alice");
        user.setPassword("alice@123");

        User userTemp = new User();
        userTemp.setUserName("Alice");
        userTemp.setPassword(new BCryptPasswordEncoder().encode("alice@123"));
        userTemp.setStatus(true);

        when(this.userRepository.getByUserName("Alice")).thenReturn(userTemp);
        when(this.authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new NotAuthenticatedException("Failed to authenticate the user"));

        NotAuthenticatedException exception = assertThrows(NotAuthenticatedException.class, () -> this.userService.verifyUser(user));
        assertEquals("Failed to authenticate the user", exception.getMessage());
    }

}
