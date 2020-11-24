package com.ama.ams.reopsitory;


//import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ama.ams.enteties.Book;
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}