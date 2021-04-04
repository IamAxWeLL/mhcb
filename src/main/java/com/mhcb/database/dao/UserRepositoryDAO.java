package com.mhcb.database.dao;

import com.mhcb.database.repository.UserRepository;
import com.mhcb.domain.User;
import com.mhcb.exception.UserNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryDAO {

    private final UserRepository userRepository;

    public UserRepositoryDAO(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User add(final String login, final String password) {
        if (userRepository.findUserByLogin(login).isPresent()) {
            throw new EntityExistsException("User with login: " + login + "already exists");
        }
        final User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        return userRepository.save(user);
    }

    public List<User> getAll() {
        final List<User> all = userRepository.findAll();
        return all.stream().filter(x -> x.getLogin().startsWith("pr")).collect(Collectors.toList());
    }

    public User getUserById(final Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id: " + userId + " not found"));
    }

    public void deleteUserById(final Long userId) {
        final User user = getUserById(userId);
        userRepository.delete(user);
    }
}
