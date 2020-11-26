package com.ama.ams.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ama.ams.enteties.Commande;
import com.ama.ams.reopsitory.CommandeRepository;
import com.ama.ams.reopsitory.BookRepository;
import com.ama.ams.exception.ResourceNotFoundException;
import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping({ "/commandes" })
public class CommandeRestController {
	private final CommandeRepository commandeRepository ;
	private final BookRepository bookRepository ;

@Autowired
public CommandeRestController(CommandeRepository commandeRepository,BookRepository bookRepository) {
this.commandeRepository = commandeRepository;
this.bookRepository = bookRepository;
}

	/*
	 * @Autowired private CommandeRepository commandeRepository;
	 * 
	 * @Autowired private BookRepository bookRepository;
	 */
	@GetMapping("/list")
	public List<Commande> getAllCommandes() {
		return (List<Commande>) commandeRepository.findAll();
	}

@PostMapping("/add/{id}")
Commande createCommande(@PathVariable (value = "id") Long id,
@Valid @RequestBody Commande commande) {

return bookRepository.findById(id).map(book ->

{

//commande.setBooks(book);
return commandeRepository.save(commande);}).orElseThrow(() -> new ResourceNotFoundException("id" + id + " not found"));

}

@PutMapping("/update/{id}/{commandeId}")
public Commande updateCommande(@PathVariable (value = "id") Long
id,
@PathVariable (value = "commandeId") Long
commandeId,
@Valid @RequestBody Commande commandeRequest) {
if(!bookRepository.existsById(id)) {
throw new ResourceNotFoundException("BookId " + id + "not found");
}
return commandeRepository.findById(commandeId).map(commande -> {
commande.setAddress(commandeRequest.getAddress());
//commande.setLabel(commandeRequest.getPicture());
return commandeRepository.save(commande);
}).orElseThrow(() -> new ResourceNotFoundException("CommandeId " +
commandeId + "not found"));
}

@DeleteMapping("/delete/{commandeId}")
public ResponseEntity<?> deleteCommande(@PathVariable (value = "commandeId")
Long commandeId) {
return commandeRepository.findById(commandeId).map(commande -> {
commandeRepository.delete(commande);
return ResponseEntity.ok().build();
}).orElseThrow(() -> new ResourceNotFoundException("Commande not foundwith id " + commandeId));
}
}
