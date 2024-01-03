package com.denizyercel.springboot.service.impl;

import com.denizyercel.springboot.entity.User;
import com.denizyercel.springboot.repository.UserRepository;
import com.denizyercel.springboot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
       return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        return optionalUser.get();
    }

    @Override
    public List<User> getAllUsers() {
       return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        User existingUser  = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updateUser = userRepository.save(existingUser);
        return updateUser;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
