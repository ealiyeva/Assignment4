package edu.ada.service.library.model;

import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

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
