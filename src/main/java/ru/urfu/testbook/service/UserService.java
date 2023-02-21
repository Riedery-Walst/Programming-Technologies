package ru.urfu.testbook.service;

import org.springframework.stereotype.Service;
import ru.urfu.testbook.dto.UserDto;
import ru.urfu.testbook.entity.User;

import java.util.List;

@Service
public interface UserService {

    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}

