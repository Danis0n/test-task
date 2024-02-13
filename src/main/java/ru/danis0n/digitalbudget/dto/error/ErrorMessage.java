package ru.danis0n.digitalbudget.dto.error;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ErrorMessage {
    private String error;
    private HttpStatus status;
}
