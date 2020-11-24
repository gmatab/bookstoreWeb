package com.ama.ams.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//import com.ama.ams.entities.;
import com.ama.ams.enteties.Book;
import com.ama.ams.reopsitory.BookRepository;
import java.util.List;
import javax.validation.Valid;
@Controller
@RequestMapping("/book/")
public class BookController {
	
	private final BookRepository BookRepository;
	@Autowired
	public BookController(BookRepository BookRepository) {
	this.BookRepository = BookRepository;
	}
	
	@GetMapping("add")
	public String showAddBookForm(Model model) {
	Book book = new Book();// object dont la valeur des attributs par defaut
	model.addAttribute("book", book);
	return "book/listBook";
	}
	
	/*@PostMapping("add")
	public String addProvider(@Valid Book book, BindingResult result, Model
	model) {
	if (result.hasErrors()) {
	return "provider/addProvider";
	}
	BookRepository.save(book);

	return "redirect:list";
	}*/
	
	
	
	
	
	
	
	
	
	
}