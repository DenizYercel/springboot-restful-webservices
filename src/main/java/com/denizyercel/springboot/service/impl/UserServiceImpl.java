package com.denizyercel.springboot.service.impl;

import com.denizyercel.springboot.dto.UserDto;
import com.denizyercel.springboot.entity.User;
import com.denizyercel.springboot.mapper.AutoUserMapper;
import com.denizyercel.springboot.mapper.UserMapper;
import com.denizyercel.springboot.repository.UserRepository;
import com.denizyercel.springboot.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository,ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {

       //User user = UserMapper.mapToUser(userDto);
       // User user = modelMapper.map(userDto,User.class);
       User user = AutoUserMapper.MAPPER.maptoUser(userDto);

       User savedUser = userRepository.save(user);

       //UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
       //UserDto savedUserDto = modelMapper.map(savedUser,UserDto.class);
        UserDto savedUserDto = AutoUserMapper.MAPPER.maptoUserDto(savedUser);
       return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();

        //return UserMapper.mapToUserDto(user);
        //return modelMapper.map(user,UserDto.class);
        return AutoUserMapper.MAPPER.maptoUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
       List<User> userList = userRepository.findAll();
      // return  userList.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
      //return  userList.stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
        return  userList.stream().map(user -> AutoUserMapper.MAPPER.maptoUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser  = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmailAdress());
        User updateUser = userRepository.save(existingUser);
        //return modelMapper.map(updateUser,UserDto.class);
        return AutoUserMapper.MAPPER.maptoUserDto(updateUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
