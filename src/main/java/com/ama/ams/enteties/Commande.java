package com.ama.ams.enteties;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "commandes")
public class Commande {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "order_date")
	private LocalDate orderDate;

	@NotBlank(message = "Address is mandatory")
	@Column(name = "address")
	private String address;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "commandes_books", joinColumns = {
			@JoinColumn(name = "commande_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false, updatable = false) })
	private Set<Book> books = new HashSet<>();


	public Commande() {
	}

	public Commande(LocalDate orderDate, String address, Set<Book> books) {
		this.orderDate = orderDate;
		this.address = address;
		this.books = books;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getCommandeDate() {
		return orderDate;
	}

	public void setCommandeDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
