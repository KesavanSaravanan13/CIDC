package com.TicketManagement.TrainTicket.service;

import com.TicketManagement.TrainTicket.dto.UserDTO;
import com.TicketManagement.TrainTicket.entity.User;
import com.TicketManagement.TrainTicket.exception.NoDataFoundException;
import com.TicketManagement.TrainTicket.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    User USER_01 = new User(null,
            "$2a$10$JMoPpfqLU/n9HXyUQqZggubQ2vEWDuQog5ndieuiFECCosOI3YMTG",
            "Charlie", "788 Oak St, Shelbyville", "556-8765", true);

    User USER_02 = new User(null,
            "$2a$10$WpC8r7R0K2oTHeyOLk4Vl.p62IqroB8up2k1izZTyBz0Kg7g9Bac.",
            "John Doe", "123 Main St, Springfield", "555-1234", true);

    User USER_03 = new User(null,
            "$2a$10$HLklNnqP6vlZs7QwTPxb/eo0Q4miZDg2WAbDIQCLBZ6jiy8EtKDHi",
            "Jane Doe", "456 Oak St, Springfield", "555-5678", false);

    User USER_04 = new User(null,
            "$2a$10$Tnrsuq6qTb8fwMtDodheP.TDMQWgDGZKjv9v1SRCxVJPapYKwehdm",
            "Alice", "789 Elm St, Springfield", "555-9012", true);

    @Test
    public void getAllUserUT(){

        List<User> userDTOList = new ArrayList<>(Arrays.asList(
                USER_01,
                USER_02,
                USER_03,
                USER_04
                ));

        when(userRepository.findAll()).thenReturn(userDTOList);
        assertThat(userRepository.findAll()).isEqualTo(userDTOList);
    }

    @Test
    public void saveUserUT(){
        User user = USER_03;

        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.saveUser(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void saveUserUT_Throws(){
        when(userRepository.findAll()).thenReturn(Collections.emptyList());
        NoDataFoundException exception = assertThrows(NoDataFoundException.class,()->userService.saveUser(USER_04));

        assertThat(exception.getMessage()).isEqualTo("No Data Found");
    }


    @Test
    public void getUserDetailsUT(){
        User user = USER_04;
        when(userRepository.save(any(User.class))).thenReturn(user); // Mock the save method
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));

        userService.saveUser(user);

        assertEquals(USER_04, userRepository.findById(USER_04.getUserId()).orElse(null));
        verify(userRepository, times(1)).save(user);

    }

    @Test
    public void getUserDetailsUT_throwsException(){
        when(userRepository.findAll()).thenReturn(Collections.emptyList());
        NoDataFoundException exception = assertThrows(NoDataFoundException.class,()->userService.getUserDetails());

        assertThat(exception.getMessage()).isEqualTo("No Data Found");
    }
}
