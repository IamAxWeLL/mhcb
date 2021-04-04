package com.mhcb.controller;

import com.mhcb.database.dao.UserRepositoryDAO;
import com.mhcb.domain.User;
import com.mhcb.domain.dto.UserDTO;
import com.mhcb.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserRepositoryDAO userRepositoryDAO;

    public UserController(final UserRepositoryDAO userRepositoryDAO) {
        this.userRepositoryDAO = userRepositoryDAO;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepositoryDAO.getAll();
    }

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable("id") final Long userId) {
        return userRepositoryDAO.getUserById(userId);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody final UserDTO dto) {
        final User user = userRepositoryDAO.add(dto.getLogin(), dto.getPassword());
        return user;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable final Long id) {
        userRepositoryDAO.deleteUserById(id);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public void handleException() {
    }
}
