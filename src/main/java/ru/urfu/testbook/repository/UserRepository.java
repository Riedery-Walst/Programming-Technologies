package ru.urfu.testbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.urfu.testbook.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

}