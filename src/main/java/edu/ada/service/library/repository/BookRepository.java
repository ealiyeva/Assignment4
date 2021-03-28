package edu.ada.service.library.repository;

import edu.ada.service.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select p from Book p where upper(p.name) like concat('%', upper(?1), '%') or upper(p.category) like concat('%', upper(?2), '%') or upper(p.author) like concat('%', upper(?3), '%')")
    List<Book> getByNames(String name, String category, String author);

}
