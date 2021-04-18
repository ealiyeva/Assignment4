package edu.ada.service.library.model;

import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String author;
    private String description;
    private String category;
    private String published_at;

    @Nullable
    private Long user_id;

    public Book(String name, String author, String description, String category, String published_at, Long userId) {
        this.name = name;
        this.author = author;
        this.description = description;
        this.category = category;
        this.published_at = published_at;
        this.user_id = userId;
    }

    public Book(Book book) {
        this.name = book.getName();
        this.author=book.getAuthor();
        this.description=book.getDescription();
        this.category=book.getCategory();
        this.published_at=book.getPublished_at();
        this.user_id=book.getUser_id();
        this.id=book.getId();
    }

    @Override
    public String toString() {

        return "Book{id=" + id + ", name=" +
                name +
                ", author=" + author +
                ", description=" + description +
                ", category=" + category +
                ", published_at=" + published_at +
                ", user_id=" + user_id +
                "}";
    }

}
