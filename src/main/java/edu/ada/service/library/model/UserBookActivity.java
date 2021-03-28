package edu.ada.service.library.model;

import java.io.Serializable;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_book_activities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserBookActivity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Nullable
    private String userName;

    private Long bookId;

    @Nullable
    private String bookName;

    private String type;

    private String date;

    public UserBookActivity(Long userID, String email, Long bookId, String bookName, String pickup, String today) {
        this.userId = userID;
        this.userName = email;
        this.bookId = bookId;
        this.bookName = bookName;
        this.type = pickup;
        this.date = today;
    }
}
