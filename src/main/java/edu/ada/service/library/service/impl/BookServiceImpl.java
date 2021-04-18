package edu.ada.service.library.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.ada.service.library.exception.NotFoundException;
import edu.ada.service.library.model.BookDTO;
import edu.ada.service.library.model.CommentModel;
import edu.ada.service.library.model.entity.CommentEntity;
import edu.ada.service.library.repository.CommentRepository;
import edu.ada.service.library.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.ada.service.library.model.Book;
import edu.ada.service.library.repository.BookRepository;
import edu.ada.service.library.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private CommentService commentService;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long BookId, Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }


    @Override
    public BookDTO getBookByID(Long book_id) {
        Optional<Book> book = bookRepository.findById(book_id);
        if (book.isEmpty()) throw new NotFoundException("Book is not found");
        Book bookModel = new Book(book.get());
        Optional<CommentEntity> commentEntity = commentRepository.findByBookExtId(String.valueOf(book_id));

        if (commentEntity.isPresent()){
            BookDTO bookDTO =  BookDTO.BookToBookDto(bookModel);
            List<CommentEntity> commentEntityList1 = bookDTO.getComments();
            commentEntityList1.add(commentEntity.get());
            bookDTO.setComments(commentEntityList1);
            return bookDTO;
        }
        else
            return   BookDTO.BookToBookDto(bookModel);
    }



    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }

    @Override
    public List<Book> getBooksBy(String... args) {
        return null;
    }

    public List<Book> getByNames(String name, String category, String author) {
        List<Book> books = bookRepository.getByNames(name, category, author);
        return books;
    }
}