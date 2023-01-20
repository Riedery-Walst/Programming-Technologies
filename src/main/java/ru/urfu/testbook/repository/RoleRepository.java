package ru.urfu.testbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.urfu.testbook.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
