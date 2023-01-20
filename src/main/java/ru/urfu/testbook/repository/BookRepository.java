package ru.urfu.testbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.urfu.testbook.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
