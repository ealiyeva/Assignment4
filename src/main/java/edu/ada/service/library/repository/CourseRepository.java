package edu.ada.service.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import edu.ada.service.library.model.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
}
