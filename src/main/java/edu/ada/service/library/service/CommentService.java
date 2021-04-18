package edu.ada.service.library.service;

import edu.ada.service.library.dto.Response;
import edu.ada.service.library.model.CommentModel;
import edu.ada.service.library.model.entity.CommentEntity;
import org.springframework.stereotype.Component;

import java.util.List;

public interface CommentService {
    List<CommentModel> getCommentByBookExtId(long id);

    Response writeCommentToBook(Long bookId, CommentEntity commentEntity);

    Response writeReplyToComment(String commenterName, CommentEntity commentEntity);
}
