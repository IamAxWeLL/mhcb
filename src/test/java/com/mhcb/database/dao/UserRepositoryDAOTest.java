package com.mhcb.database.dao;

import com.mhcb.database.repository.UserRepository;
import com.mhcb.domain.User;
import com.mhcb.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UserRepositoryDAO.class})
@ExtendWith(SpringExtension.class)
public class UserRepositoryDAOTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserRepositoryDAO userRepositoryDAO;

    @Test
    public void shouldAddUser() {
        User user = new User(123L, "loginTest", "passwordTest");
        when(this.userRepository.save((User) any())).thenReturn(user);
        when(this.userRepository.findUserByLogin(anyString())).thenReturn(Optional.<User>empty());
        assertSame(user, this.userRepositoryDAO.add("loginTest", "passwordTest"));
        verify(this.userRepository).save((User) any());
        verify(this.userRepository).findUserByLogin(anyString());
    }

    @Test
    public void shouldGetAllUsers() {
        when(this.userRepository.findAll()).thenReturn(new ArrayList<User>());
        assertTrue(this.userRepositoryDAO.getAll().isEmpty());
        verify(this.userRepository).findAll();
    }

    @Test
    public void shouldGetUserById() {
        User user = new User(123L, "loginTest", "passwordTest");
        Optional<User> ofResult = Optional.<User>of(user);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(user, this.userRepositoryDAO.getUserById(123L));
        verify(this.userRepository).findById((Long) any());
    }

    @Test
    public void shouldDeleteUserById() {
        doNothing().when(this.userRepository).delete((User) any());
        when(this.userRepository.findById((Long) any())).thenReturn(Optional.<User>empty());
        assertThrows(UserNotFoundException.class, () -> this.userRepositoryDAO.deleteUserById(123L));
        verify(this.userRepository).findById((Long) any());
    }
}
