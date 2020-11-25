package com.ama.ams.controllers;

import com.ama.ams.enteties.Book;
import com.ama.ams.enteties.Commande;
import com.ama.ams.reopsitory.BookRepository;
import com.ama.ams.reopsitory.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/commandes")
public class CommandeController {

	@Autowired
	private CommandeRepository CommandeRepository;

	@Autowired
	private BookRepository bookRepository;

	@GetMapping("/list")
	public String listCommandes(Model model) {
		List<Commande> lp = (List<Commande>) CommandeRepository.findAll();
		if (lp.size() == 0)
			lp = null;
		model.addAttribute("commandes", lp);
		return "commandes/listCommande";
	}

	@GetMapping("/add")
	public String showAddCommandeForm(Model model) {
		Commande Commande = new Commande();
		Commande.setCommandeDate(LocalDate.now());
		model.addAttribute("newCommande", Commande);
		model.addAttribute("bookList", bookRepository.findAll());
		return "commandes/addCommande";
	}

	@PostMapping("/add")
	public String addCommande(@Valid Commande Commande, BindingResult result) {
//        if(result.hasErrors()){
//            return result.toString();
//        }else {
		Commande.setCommandeDate(LocalDate.now());
		CommandeRepository.save(Commande);
		return "redirect:list";
//        }

	}

	@GetMapping("/delete/{id}")
	public String deleteCommande(@PathVariable("id") long id, Model model) {
		Commande Commande = CommandeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Commande Id:" + id));
		CommandeRepository.delete(Commande);
		model.addAttribute("commandes", CommandeRepository.findAll());
		return "redirect:../list";
	}

	@GetMapping("/edit/{id}")
	public String showCommandeFormToUpdate(@PathVariable("id") long id, Model model) {
		Commande Commande = CommandeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Commande Id:" + id));

		model.addAttribute("commande", Commande);
		return "commandes/UpdateCommande";
	}

	@PostMapping("edit/{id}")
	public String updateBook(@Valid Commande Commande, @PathVariable("id") long id, BindingResult result, Model model) {
		Commande.setId(id);
		CommandeRepository.save(Commande);
		model.addAttribute("book", bookRepository.findAll());
		return "redirect:../list";
	}

	@GetMapping("/show/{id}")
	public String showCommandeDetails(@PathVariable("id") long id, Model model) {
		Commande Commande = CommandeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Commande Id:" + id));
		model.addAttribute("commande", Commande);

		return "commandes/showCommande";
	}
}
