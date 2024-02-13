package ru.danis0n.digitalbudget.dto.user;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {
    @Nullable
    private Long id;

    @Nullable
    private String name;

    @Nullable
    @Pattern(regexp = "^[A-Za-z]+$", message = "Username must contain only Latin symbols")
    private String username;

    @Nullable
    @Email(message = "Email should be valid")
    private String email;
}