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
	
	@GetMapping("list")
	// @ResponseBody
	public String listProviders(Model model) {
		List<Book> lp = (List<Book>) BookRepository.findAll();
		if (lp.size() == 0)
			lp = null;
		model.addAttribute("book", lp);
		return "book/listBook";
		// System.out.println(lp);
		// return "Nombre de fournisseur = " + lp.size();
	}

	
	
	
	@GetMapping("add")
	//@ResponseBody

	public String showAddBookForm(Model model) {
	Book book = new Book();// object dont la valeur des attributs par defaut
	model.addAttribute("book", book);
	return "book/addBook";
	}
	
	@PostMapping("add")
	//@ResponseBody

	public String addBook(@Valid Book book, BindingResult result, Model
	model) {
	if (result.hasErrors()) {
	return "book/addBook";
	}
	BookRepository.save(book);

	return "redirect:list";
	}
	
	
	
	@GetMapping("delete/{id}")
	public String deleteBook(@PathVariable("id") long id, Model model) {
	// long id2 = 100L;
	Book book = BookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalidprovider Id:" + id));
	//System.out.println("suite du programme...")
	BookRepository.delete(book);
	/*
	* model.addAttribute("providers", providerRepository.findAll()); return
	* "provider/listProviders";
	*/
	return "redirect:../list";
	}
	
	@GetMapping("edit/{id}")
	public String showBookFormToUpdate(@PathVariable("id") long id, Model model) {
		Book book = BookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("InvalidBook Id:" + id));
		model.addAttribute("book", book);
		return "book/updateBook";
	}

	@PostMapping("update")
	public String updateBook(@Valid Book book, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "book/updateBook";
		}
		BookRepository.save(book);
		return "redirect:list";
	}
	
	
	
	
}