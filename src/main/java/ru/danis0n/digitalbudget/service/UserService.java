package ru.danis0n.digitalbudget.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.danis0n.digitalbudget.dto.user.RegisterDto;
import ru.danis0n.digitalbudget.dto.user.UpdateDto;
import ru.danis0n.digitalbudget.dto.user.UserDto;
import ru.danis0n.digitalbudget.exception.NotFoundException;
import ru.danis0n.digitalbudget.exception.UserExistsException;
import ru.danis0n.digitalbudget.mapper.UserMapper;
import ru.danis0n.digitalbudget.model.User;
import ru.danis0n.digitalbudget.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Transactional
    public UserDto register(RegisterDto userDto) {
        Optional<User> createdUser = userRepository
                .findByEmailOrUsername(userDto.getEmail(), userDto.getUsername());

        if (createdUser.isPresent()) {
            User user = createdUser.get();

            if (user.getEmail().equals(userDto.getEmail())) {
                throw new UserExistsException("User with current email already exists!");
            } else {
                throw new UserExistsException("User with current username already exists!");
            }
        }

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());

        user = userRepository.save(user);
        logger.info("User with username {} and email {} was saved", user.getUsername(), user.getEmail() );
        return UserMapper.mapToDto.apply(user);
    }

    public UserDto findById(Long userId) {
        return UserMapper.mapToDto.apply(findModelById(userId));
    }

    public User findModelById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new NotFoundException("User with current id (" + userId + ") was not found!");
        }

        return user.get();
    }

    @Transactional
    public UserDto update(Long userId, UpdateDto userDto) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            logger.error("User with current id (" + userId + ") was not found!");
            throw new NotFoundException("User with current id (" + userId + ") was not found!");
        }

        User user = userOptional.get();

        if (userDto.getUsername() != null) {
            user.setUsername(userDto.getUsername());
        }

        if (userDto.getName() != null) {
            user.setName(userDto.getName());
        }

        user = userRepository.save(user);
        logger.info("User with username {} and email {} was updated", user.getUsername(), user.getEmail() );
        return UserMapper.mapToDto.apply(user);
    }

    public Boolean delete(Long userId) {
        userRepository.deleteById(userId);
        return true;
    }

    public Boolean isUserExists(Long userId) {
        return userRepository.existsById(userId);
    }

}
