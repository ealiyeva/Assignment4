package edu.ada.service.library.controller;


import edu.ada.service.library.dto.Response;
import edu.ada.service.library.model.entity.CommentEntity;
import edu.ada.service.library.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;


    @PostMapping("/addCommentToBook")
    public ResponseEntity<?> addCommentToBook(@RequestParam Long bookId, @RequestBody CommentEntity commentEntity){
        Response response = commentService.writeCommentToBook(bookId, commentEntity);
        if (response.getCode()==400)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/addReplyToBook")
    public ResponseEntity<?> addReplyToBook(@RequestParam String commenterName, @RequestBody CommentEntity commentEntity){
        Response response = commentService.writeReplyToComment(commenterName, commentEntity);
        if (response.getCode()==400)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
