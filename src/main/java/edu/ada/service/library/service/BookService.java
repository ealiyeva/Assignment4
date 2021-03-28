package edu.ada.service.library.service;

import java.util.List;
import java.util.Optional;

import edu.ada.service.library.model.Book;

public interface BookService {

    public void addBook(Book book);

//    public Book getBook(Long bookId);

    public Book updateBook(Long BookId,Book book);

    public void deleteBook(Long bookId);

    public List<Book> getAllBooks();

    public List<Book> getByNames(String name, String category, String author);

    public List<Book> getBooksBy(String... args);

    Optional<Book> findById(Long id);

}