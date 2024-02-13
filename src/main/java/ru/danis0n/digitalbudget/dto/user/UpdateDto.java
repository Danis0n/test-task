package ru.danis0n.digitalbudget.dto.user;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateDto {
    @Nullable
    @Pattern(regexp = "^[A-Za-z]+$", message = "Username must contain only Latin symbols")
    private String username;

    @Nullable
    private String name;
}