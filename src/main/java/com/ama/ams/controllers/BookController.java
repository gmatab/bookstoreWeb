package com.ama.ams.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

//import com.ama.ams.entities.;
import com.ama.ams.enteties.Book;
import com.ama.ams.reopsitory.BookRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.validation.Valid;
@Controller
@RequestMapping("/book/")
public class BookController {
	public static String uploadDirectory =
			System.getProperty("user.dir")+"/src/main/resources/static/uploads";
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
	
	/*@PostMapping("add")
	//@ResponseBody

	public String addBook(@Valid Book book, BindingResult result, Model
	model) {
	if (result.hasErrors()) {
	return "book/addBook";
	}
	BookRepository.save(book);

	return "redirect:list";
	}*/
	
	@PostMapping("add")
	 //@ResponseBody
	 public String addBook(@Valid Book book, BindingResult result,
	//@RequestParam(name = "providerId", required = false) Long p,
	 @RequestParam("files") MultipartFile[] files) {

	 /*Provider provider = providerRepository.findById(p).orElseThrow(()-> new IllegalArgumentException("Invalid
	provider Id:" + p));
	 article.setProvider(provider);*/

	 /// part upload

		StringBuilder fileName = new StringBuilder();
		 MultipartFile file = files[0];
		 Path fileNameAndPath = Paths.get(uploadDirectory,
		file.getOriginalFilename());

		 fileName.append(file.getOriginalFilename());
		 try {
		Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
		e.printStackTrace();
		}

		book.setCover(fileName.toString());
		 BookRepository.save(book);
		 return "redirect:list";
	 //return article.getLabel() + " " +article.getPrice() + " " +p.toString();
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