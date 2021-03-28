package edu.ada.service.library.controller;

import edu.ada.service.library.model.Book;
import edu.ada.service.library.repository.BookRepository;
import edu.ada.service.library.security.UserPrincipal;
import edu.ada.service.library.service.BookService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import edu.ada.service.library.model.UserBookActivity;
import edu.ada.service.library.service.UserBookActivityService;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class LibraryController {

    private BookRepository bookRepository;

    Date date = new Date();
    String strDateFormat = "yyyy-MM-dd HH:mm:ss a";
    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
    String formattedDate= dateFormat.format(date);

    @Autowired
    private BookService bookService;

    @Autowired
    private UserBookActivityService userBookActivityService;

    @GetMapping
    public
    @ResponseBody
    ResponseEntity<Object> getListBooks(HttpServletRequest request) {

        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String author = request.getParameter("author");

        List<Book> listBooks = null;
        if (name != null || category != null || author != null) {
            listBooks = bookService.getByNames(name, category, author);
        } else {
            listBooks = bookService.getAllBooks();
        }

        List<JSONObject> books = new ArrayList<JSONObject>();

        for (Book b: listBooks) {
            JSONObject book = new JSONObject();
            book.put("id", b.getId());
            book.put("name", b.getName());
            book.put("author", b.getAuthor());
            book.put("description", b.getDescription());
            book.put("category", b.getCategory());
            book.put("published_at", b.getPublished_at());

            books.add(book);
        }
        return new ResponseEntity<Object>(books, HttpStatus.OK);
    }

    @PutMapping("pickup/{id}")
    public @ResponseBody ResponseEntity<Object> pickupBook(@PathVariable String id, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        JSONObject response = new JSONObject();
        Optional<Book> book = bookService.findById(Long.parseLong(id));
        Long userId = null;
        Long bookId = null;

        if (book.isPresent()) {
            userId = book.get().getUser_id();
            bookId = book.get().getId();
        } else {
            response.put("message", "Book with given id not found in out library");
            return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
        }

        if (userId != null && !userId.equals(userPrincipal.getId())) {
            response.put("message", "This book is belong to user id : " + userId);
            return new ResponseEntity<Object>(response, HttpStatus.ACCEPTED);
        } else {
            book.get().setUser_id(userPrincipal.getId());
            bookService.updateBook(bookId, book.get());

            UserBookActivity userBookActivity = new UserBookActivity(userPrincipal.getId(), userPrincipal.getEmail(), book.get().getId(), book.get().getName(), "pickup", formattedDate);
            userBookActivityService.add(userBookActivity);
            response.put("message", "Successfully picked up");
        }

        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PutMapping("drop/{id}")
    public @ResponseBody ResponseEntity<Object> dropBook(@PathVariable String id, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        JSONObject response = new JSONObject();
        Optional<Book> book = bookService.findById(Long.parseLong(id));
        Long userId = null;
        Long bookId = null;

        if (book.isPresent()) {
            userId = book.get().getUser_id();
            bookId = book.get().getId();
        } else {
            response.put("message", "Book with given id not found in out library");
            return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
        }

        if (userId == null || !userId.equals(userPrincipal.getId())) {
            response.put("message", "This book is not  belong to you");
            return new ResponseEntity<Object>(response, HttpStatus.ACCEPTED);
        } else {
            book.get().setUser_id(null);
            bookService.updateBook(bookId, book.get());
            UserBookActivity userBookActivity = new UserBookActivity(userPrincipal.getId(), userPrincipal.getEmail(), book.get().getId(), book.get().getName(), "drop", formattedDate);
            userBookActivityService.add(userBookActivity);
            response.put("message", "Successfully dropped");
        }

        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @GetMapping("my")
    public @ResponseBody ResponseEntity<Object> getUserBooks(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<Book> listBooks=bookService.getAllBooks();

        List<JSONObject> books = new ArrayList<JSONObject>();

        for (Book b: listBooks) {
            if (b.getUser_id() != null && b.getUser_id().equals(userPrincipal.getId())) {
                JSONObject book = new JSONObject();
                book.put("id", b.getId());
                book.put("name", b.getName());
                book.put("author", b.getAuthor());
                book.put("description", b.getDescription());
                book.put("category", b.getCategory());
                book.put("published_at", b.getPublished_at());

                books.add(book);
            }
        }
        return new ResponseEntity<Object>(books, HttpStatus.OK);
    }

    @GetMapping("activities")
    public @ResponseBody ResponseEntity<Object> getActivities(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        List <UserBookActivity> listActivities = userBookActivityService.getAllActivities();
        List<JSONObject> activities = new ArrayList<JSONObject>();

        for (UserBookActivity activity: listActivities) {
            if (activity.getUserId() != null && activity.getUserId().equals(userPrincipal.getId())) {
                JSONObject a = new JSONObject();
                a.put("id", activity.getId());
                a.put("user_id", activity.getUserId());
                a.put("user_email", activity.getUserName());
                a.put("book_id", activity.getBookId());
                a.put("book_name", activity.getBookName());
                a.put("type", activity.getType());
                a.put("date", activity.getDate());

                activities.add(a);
            }
        }

        return new ResponseEntity<Object>(activities, HttpStatus.OK);
    }

}
