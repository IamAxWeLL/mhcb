package com.mhcb.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mhcb.database.dao.UserRepositoryDAO;
import com.mhcb.database.repository.UserRepository;
import com.mhcb.domain.User;
import com.mhcb.domain.dto.UserDTO;

import java.util.ArrayList;

import java.util.Optional;

import org.hamcrest.Matchers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @MockBean
    private UserRepositoryDAO userRepositoryDAO;

    @Test
    public void shouldGetUserById() throws Exception {
        User user = new User(123L, "loginTest", "passwordTest");
        when(this.userRepositoryDAO.getUserById((Long) any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{id}", 1L);
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.containsString("{\"id\":123,\"login\":\"loginTest\",\"password\":\"passwordTest\"}")));
    }

    @Test
    public void shouldAddUser() {
        UserRepository userRepository = mock(UserRepository.class);
        UserController userController = new UserController(new UserRepositoryDAO(userRepository));
        userController.addUser(new UserDTO("loginTest", "passwordTest"));
        verify(userRepository).findUserByLogin(anyString());
    }

    @Test
    public void shouldDeleteUserById() throws Exception {
        doNothing().when(this.userRepositoryDAO).deleteUserById((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/users/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void shouldGetAllUsers() throws Exception {
        when(this.userRepositoryDAO.getAll()).thenReturn(new ArrayList<User>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
    }
}
