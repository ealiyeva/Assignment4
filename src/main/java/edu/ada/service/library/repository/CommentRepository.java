package edu.ada.service.library.repository;

import edu.ada.service.library.model.entity.CommentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends MongoRepository<CommentEntity, String> {
    Optional<List<CommentEntity>> findAllByBookExtId(long id);

    Optional<CommentEntity> findByCommentAuthorName(String name);

    Optional<CommentEntity> findByBookExtId(String bookExtId);
}
