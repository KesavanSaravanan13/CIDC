package com.TicketManagement.TrainTicket.controller;

import com.TicketManagement.TrainTicket.dto.UserDTO;
import com.TicketManagement.TrainTicket.entity.User;
import com.TicketManagement.TrainTicket.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    List<UserDTO> userDTOList = new ArrayList<>(Arrays.asList(
            new UserDTO(1L, "Charlie", "788 Oak St, Shelbyville", "556-8765", true),
            new UserDTO(2L, "John Doe", "123 Main St, Springfield", "555-1234", true),
            new UserDTO(3L, "Jane Doe", "456 Oak St, Springfield", "555-5678", false),
            new UserDTO(4L, "Alice", "789 Elm St, Springfield", "555-9012", true)
    ));

    List<User> userList = new ArrayList<>(Arrays.asList(
            new User(1L, "Charlie", "788 Oak St, Shelbyville", "556-8765", true),
            new User(2L, "John Doe", "123 Main St, Springfield", "555-1234", true),
            new User(3L, "Jane Doe", "456 Oak St, Springfield", "555-5678", false),
            new User(4L, "Alice", "789 Elm St, Springfield", "555-9012", true)
    ));

//    @BeforeEach
//    public void checkToken(){
//
//    }

    @Test
    public void getAllUsersUT() {

        when(userService.getAllUsers()).thenReturn(userDTOList);
        assertThat(userController.getAllUsers()).isEqualTo(userDTOList);
    }

    @Test
    public void createUserUT() {
        User user = User.builder()
                .userId(1L)
                .userName("Alice")
                .address("4th street")
                .password("alice@123")
                .phoneNumber("e214")
                .status(true)
                .build();

        userController.createUser(user);

        verify(userService, times(1)).saveUser(user);
    }


    @Test
    public void getUserByIdUT() {
        Long userId = 1L;
        UserDTO userDTO = new UserDTO(1L, "Alice", "4th street", "e214", true);
        when(userService.getUserById(userId)).thenReturn(userDTO);

        UserDTO result = userController.getUserById(userId);

        assertThat(result).isEqualTo(userDTO);
        verify(userService, times(1)).getUserById(userId);
    }


    @Test
    public void loginUserUT() {
        User user = User.builder()
                .userId(1L)
                .userName("Alice")
                .password("alice@123")
                .build();
        when(userService.verifyUser(user)).thenReturn("Token");

        String result = userController.loginUser(user);

        assertThat(result).isEqualTo("Token");
        verify(userService, times(1)).verifyUser(user);
    }

    @Test
    public void deleteUserByIdUT() {
        Long userId = 1L;
        UserDTO userDTO = new UserDTO(1L, "Alice", "4th street", "e214", true);
        when(userService.deleteUser(userId)).thenReturn("Deleted");
        userController.deleteUser(userId);
        assertThat(userController.getUserById(userId)).isNull();
        verify(userService, times(1)).deleteUser(userId);
    }

}
