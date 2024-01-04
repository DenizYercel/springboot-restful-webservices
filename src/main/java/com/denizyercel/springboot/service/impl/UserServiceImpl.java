package com.denizyercel.springboot.service.impl;

import com.denizyercel.springboot.dto.UserDto;
import com.denizyercel.springboot.entity.User;
import com.denizyercel.springboot.exception.EmailAlreadyExistsException;
import com.denizyercel.springboot.exception.ResourceNotFoundException;
import com.denizyercel.springboot.mapper.AutoUserMapper;
import com.denizyercel.springboot.repository.UserRepository;
import com.denizyercel.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {

        Optional<User> userOptional = userRepository.findByEmail(userDto.getEmail());

        if(userOptional.isPresent()){
            throw  new EmailAlreadyExistsException("Email Already Exist For User");
        }
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
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User","id",userId)
        );

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
        User existingUser  = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User","id",user.getId())
        );
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updateUser = userRepository.save(existingUser);
        //return modelMapper.map(updateUser,UserDto.class);
        return AutoUserMapper.MAPPER.maptoUserDto(updateUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser  = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User","id",userId)
        );
        userRepository.deleteById(userId);
    }
}
