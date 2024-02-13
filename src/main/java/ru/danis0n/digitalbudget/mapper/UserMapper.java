package ru.danis0n.digitalbudget.mapper;

import ru.danis0n.digitalbudget.dto.user.UserDto;
import ru.danis0n.digitalbudget.model.User;

import java.util.function.Function;

public class UserMapper {

    public static Function<User, UserDto> mapToDto =
            user -> UserDto.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .name(user.getName())
                    .username(user.getUsername())
                    .build();

}
