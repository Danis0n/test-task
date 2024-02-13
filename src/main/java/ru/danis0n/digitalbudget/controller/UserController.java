package ru.danis0n.digitalbudget.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.danis0n.digitalbudget.dto.user.RegisterDto;
import ru.danis0n.digitalbudget.dto.user.UpdateDto;
import ru.danis0n.digitalbudget.dto.user.UserDto;
import ru.danis0n.digitalbudget.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> register(@Valid
                            @RequestBody RegisterDto userDto) {
        return ResponseEntity.ok(userService.register(userDto));
    }

    @GetMapping("secured")
    public ResponseEntity<UserDto> findById(@RequestHeader("User-Id") Long userId) {
        return ResponseEntity.ok(userService.findById(userId));
    }

    @PutMapping("secured")
    public ResponseEntity<UserDto> update(@Valid @RequestBody UpdateDto userDto,
                          @RequestHeader("User-Id") Long userId) {
        return ResponseEntity.ok(userService.update(userId, userDto));
    }

    @DeleteMapping("secured")
    public ResponseEntity<Boolean> delete(@RequestHeader("User-Id") Long userId) {
        return ResponseEntity.ok(userService.delete(userId));
    }

}
