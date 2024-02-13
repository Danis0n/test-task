package ru.danis0n.digitalbudget.dto.user;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterDto {
    @Nullable
    private String name;

    @NotNull
    @Pattern(regexp = "^[A-Za-z]+$", message = "Username must contain only Latin symbols")
    private String username;

    @NotNull
    @Email(message = "Email should be valid")
    private String email;

}
