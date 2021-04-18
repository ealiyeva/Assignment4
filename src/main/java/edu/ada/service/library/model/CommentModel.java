package edu.ada.service.library.model;

import edu.ada.service.library.model.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentModel {
    private String id;
    private String author_name;
    private String content;

    private List<CommentModel> replies;

    public CommentModel(CommentEntity entity){
        this.id= entity.getId();
        this.author_name=entity.getCommentAuthorName();
        this.content =entity.getCommentContent();
    }

    public static CommentModel commentEntityToCommentModel(CommentEntity commentEntity){
        CommentModel commentModel  =new CommentModel();
        commentModel.setAuthor_name(commentEntity.getCommentAuthorName());
        commentModel.setContent(commentEntity.getCommentContent());
        return commentModel;
    }
}
