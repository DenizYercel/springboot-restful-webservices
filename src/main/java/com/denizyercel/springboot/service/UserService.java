package com.denizyercel.springboot.service;

import com.denizyercel.springboot.dto.UserDto;
import com.denizyercel.springboot.entity.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);
    UserDto getUserById(Long userId);
    List<UserDto> getAllUsers();
    UserDto updateUser(UserDto user);
    void deleteUser(Long userId);
}
