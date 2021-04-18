package edu.ada.service.library.model;



import edu.ada.service.library.model.entity.CommentEntity;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO implements Serializable {

    private Long id;

    private String name;
    private String author;
    private String description;
    private String category;
    private String published_at;

    private Long user_id;

    private List<CommentEntity> comments = new ArrayList<>(1);

    public static BookDTO BookToBookDto(Book book){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setCategory(book.getCategory());
        bookDTO.setName(book.getName());
        bookDTO.setPublished_at(bookDTO.getPublished_at());
        bookDTO.setUser_id(book.getUser_id());
        return bookDTO;
    }

    @Override
    public String toString() {

        return "BookDTO{id=" + id + ", name=" +
                name +
                ", author=" + author +
                ", description=" + description +
                ", category=" + category +
                ", published_at=" + published_at +
                ", user_id=" + user_id +
                "}";
    }

    public List<CommentEntity> getComments() {
        return comments;
    }
}

