package edu.ada.service.library.service.impl;

import edu.ada.service.library.dto.Response;
import edu.ada.service.library.model.Book;
import edu.ada.service.library.model.CommentModel;
import edu.ada.service.library.model.entity.CommentEntity;
import edu.ada.service.library.repository.BookRepository;
import edu.ada.service.library.repository.CommentRepository;
import edu.ada.service.library.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;


    @Autowired
    private BookRepository bookRepository;


    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    @Override
    public List<CommentModel> getCommentByBookExtId(long id) {

        List<CommentModel> comments = new ArrayList<>(1);
        Optional<List<CommentEntity>> result = commentRepository.findAllByBookExtId(id);
        //no comments
        if (result.isEmpty()) return comments;


        if (result.get().size() > 0) {
            result.get().stream().forEach((commentEntity -> {
                comments.add(new CommentModel(commentEntity));
            }));


            //return list with full of comments
        }
        return comments;
    }


    @Override
    public Response writeCommentToBook(Long bookId, CommentEntity commentEntity) {
        Response response = new Response();
        System.out.println("BookID " + bookId);
        Optional<Book> book = bookRepository.findById(bookId);
        System.out.println("book " + book.get());
        if (book.isEmpty()) return response.setCode(404);
        else {
            CommentEntity commentEntity1  = new CommentEntity(String.valueOf(bookId), commentEntity.getCommentAuthorName(), commentEntity.getCommentContent(), null);
            commentRepository.save(commentEntity1);
            return response.setCode(201);
        }
    }

    @Override
    public Response writeReplyToComment(String commenterName, CommentEntity commentEntity) {
        Response response = new Response();
        Optional<CommentEntity> commentEntity1 =  commentRepository.findByCommentAuthorName(commenterName);
        if (commentEntity1.isEmpty()) return response.setCode(404);
        else {
            String bookId =commentEntity1.get().getBookExtId();
            CommentEntity commentEntity2 = new CommentEntity(bookId,commentEntity.getCommentAuthorName(),commentEntity.getCommentContent(),null);
            List<CommentEntity> commentEntityList = commentEntity1.get().getReplies();
            commentEntityList.add(commentEntity2);
            commentRepository.save(commentEntity1.get());
            return response.setCode(201);
        }
    }




}
