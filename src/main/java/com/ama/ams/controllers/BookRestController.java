package com.ama.ams.controllers;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ama.ams.enteties.Book;
import com.ama.ams.exception.ResourceNotFoundException;
import com.ama.ams.reopsitory.BookRepository;
@RestController
@RequestMapping({"/books"})
public class BookRestController {
	@Autowired
	private BookRepository bookRepository;

	@GetMapping("/list")
	public List<Book> getAllProvider() {
		return (List<Book>) bookRepository.findAll();
	}

	@PostMapping("/add")
	public Book createBook(@Valid @RequestBody Book book) {
		return bookRepository.save(book);
	}

	@PutMapping("/{id}")
	public Book updateBook(@PathVariable Long id, @Valid @RequestBody Book bookRequest) {
		return bookRepository.findById(id).map(book -> {
			book.setAuthor(bookRequest.getAuthor());
			book.setTitle(bookRequest.getTitle());
			book.setReleaseDate(bookRequest.getReleaseDate());
			return bookRepository.save(book);
		}).orElseThrow(() -> new ResourceNotFoundException("id " + id + " not found"));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable Long id) {
		return bookRepository.findById(id).map(book -> {
			bookRepository.delete(book);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("id " + id + " not found"));
	}

}
