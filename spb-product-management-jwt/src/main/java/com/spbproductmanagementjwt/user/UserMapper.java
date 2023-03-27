package com.spbproductmanagementjwt.user;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    private static final ModelMapper mapper = new ModelMapper();

    private UserMapper(){}

    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass){
        return source
                .stream()
                .map(item -> mapper.map(item, targetClass))
                .collect(Collectors.toList());
    }

    public static UserDTO userMapUserDTO(User user) {
        return mapper.map(user, UserDTO.class);
    }

    public static User userDTOMapUser(UserDTO userDTO) {
        return mapper.map(userDTO, User.class);
    }
}
