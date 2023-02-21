package ru.urfu.testbook.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.urfu.testbook.entity.Book;
import ru.urfu.testbook.entity.UserAction;
import ru.urfu.testbook.repository.BookRepository;
import ru.urfu.testbook.repository.UserActionRepository;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserActionRepository userActionRepository;

    @GetMapping("/list")
    public ModelAndView getAllBooks() {
        log.info("/list -> connection");
        ModelAndView mav = new ModelAndView("list-books");
        mav.addObject("books", bookRepository.findAll());
        return mav;
    }

    @GetMapping("/addBookForm")
    public ModelAndView addBookForm() {
        ModelAndView mav = new ModelAndView("add-book-form");
        Book book = new Book();
        mav.addObject("book", book);
        return mav;
    }

    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute Book book) {
        bookRepository.save(book);

        UserAction userAction = new UserAction();
        userAction.setDescription(book + "was created by " + getCurrentUsername());
        userAction.setDateActions(new Date());
        userActionRepository.save(userAction);

        return "redirect:/list";

    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long bookId) {
        ModelAndView mav = new ModelAndView("add-book-form");
        Optional<Book> optionalBook= bookRepository.findById(bookId);
        Book book = new Book();
        if (optionalBook.isPresent()) {
            book = optionalBook.get();
        }
        mav.addObject("book", book);

        UserAction userAction = new UserAction();
        userAction.setDescription(book + "was updated by " + getCurrentUsername());
        userAction.setDateActions(new Date());
        userActionRepository.save(userAction);

        return mav;
    }

    @GetMapping("/deleteBook")
    public String deleteBook(@RequestParam Long bookId) {
        bookRepository.deleteById(bookId);

        UserAction userAction = new UserAction();
        userAction.setDescription(bookId + "was deleted by " + getCurrentUsername());
        userAction.setDateActions(new Date());
        userActionRepository.save(userAction);


        return "redirect:/list";
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}