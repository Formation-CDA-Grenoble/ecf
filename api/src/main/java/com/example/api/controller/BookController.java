package com.example.api.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.example.api.entity.Book;
import com.example.api.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/books")
@CrossOrigin
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("")
    public List<Book> getAllBooks() {
        return bookRepository.findAllByOrderByPublishedAtDesc();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable(value = "id") Long id) {
        return bookRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found")
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteBookById(@PathVariable(value = "id") Long id) {
        bookRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found")
        );
        bookRepository.deleteById(id);
    }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Book createBook(@Valid @RequestBody Book book) {
        book.setPublishedAt(new Date());
        return bookRepository.save(book);
    }

    @PatchMapping("/{id}")
    public Book updatePartialBook(@PathVariable(value = "id") Long id, @Valid @RequestBody Book newBook) {
        Book book = bookRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found")
        );
        if (newBook.getTitle() != null) {
            book.setTitle(newBook.getTitle());
        }
        if (newBook.getSummary() != null) {
            book.setSummary(newBook.getSummary());
        }
        if (newBook.getPublishedAt() != null) {
            book.setPublishedAt(newBook.getPublishedAt());
        }
        if (newBook.getChapters() != null) {
            book.setChapters(newBook.getChapters());
        }
        if (newBook.getTags() != null) {
            book.setTags(newBook.getTags());
        }
        return bookRepository.save(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable(value = "id") Long id, @Valid @RequestBody Book newBook) {
        Book book = bookRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found")
        );
        book.setTitle(newBook.getTitle());
        book.setSummary(newBook.getSummary());
        book.setPublishedAt(newBook.getPublishedAt());
        book.setChapters(newBook.getChapters());
        book.setTags(newBook.getTags());
        return bookRepository.save(book);
    }
}
