package edu.ada.service.library.repository;

import edu.ada.service.library.model.UserBookActivity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBookActivityRepository extends CrudRepository<UserBookActivity, Long> {
}
