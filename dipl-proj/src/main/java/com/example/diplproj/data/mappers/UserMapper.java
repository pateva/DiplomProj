package com.example.diplproj.data.mappers;
import com.example.diplproj.data.dtos.UserDto;
import com.example.diplproj.data.models.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    User userDtoToUser(UserDto userDto);
}
