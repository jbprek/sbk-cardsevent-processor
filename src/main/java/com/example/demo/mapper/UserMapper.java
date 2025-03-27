package com.example.demo.mapper;

import com.example.demo.avro.User;
import com.example.demo.dto.UserDTO;

public class UserMapper {
    public static User toAvro(UserDTO dto) {
        return User.newBuilder()
                .setName(dto.getName())
                .setAge(dto.getAge())
                .build();
    }

    public static UserDTO fromAvro(User user) {
        return new UserDTO(user.getName(), user.getAge());
    }
}
