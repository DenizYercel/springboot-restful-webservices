package com.denizyercel.springboot.mapper;

import com.denizyercel.springboot.dto.UserDto;
import com.denizyercel.springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {

    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);
    UserDto maptoUserDto(User user);
    User maptoUser(UserDto userDto);
}
