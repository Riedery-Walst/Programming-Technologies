package ru.urfu.testbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.urfu.testbook.entity.UserAction;

@Repository
public interface UserActionRepository extends JpaRepository<UserAction, Long> {
}
