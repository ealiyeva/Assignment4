package edu.ada.service.library.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "CommentCollection")
public class CommentEntity {

    @Id
    private String id;
    private String bookExtId;
    private String commentAuthorName;
    private String commentContent;


    private List<CommentEntity> replies = new ArrayList<>(1);


    public CommentEntity(String bookExtId, String commentAuthorName, String commentContent, List<CommentEntity> replies) {
        this.bookExtId = bookExtId;
        this.commentAuthorName = commentAuthorName;
        this.commentContent = commentContent;
        this.replies = replies;
    }
}

