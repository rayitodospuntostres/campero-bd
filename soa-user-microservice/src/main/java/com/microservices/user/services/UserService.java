package com.microservices.user.services;

import com.microservices.user.models.User;

import java.util.List;

public interface UserService {

    User save(User user);

    public User findById(Long id);

    public void deleteById(Long id);

    List<User> findAll();

    public User findByUsername(String username);


}
