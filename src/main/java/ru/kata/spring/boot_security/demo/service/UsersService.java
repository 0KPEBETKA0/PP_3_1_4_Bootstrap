package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UsersService {

    List<User> getAllUsers();

    void saveUser(User user);

    User getUserById(Long id);

    User getUserByUsername(String username);

    void updateUser(Long id, User user);

    void removeUserById(Long id);
}
