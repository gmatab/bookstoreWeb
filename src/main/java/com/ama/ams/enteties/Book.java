package com.ama.ams.enteties;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.ama.ams.enteties.Commande;
@Entity
@Table(name = "book")
public class Book {
@Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private long id;
 @NotBlank(message = "title is mandatory")
 @Column(name = "title")
 private String title;

 @NotBlank(message = "author is mandatory")
 @Column(name = "author")
 private String author;

 @NotNull(message = "price is mandatory")
 @Column(name = "price")
 private double price;
 
 @NotBlank(message = "release date is mandatory")
 @Column(name = "releaseDate")
 private String releaseDate;
 @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
	Set<Commande> commandes = new HashSet<>();

 
 public Set<Commande> getCommandes() {
	return commandes;
}

public void setCommandes(Set<Commande> commandes) {
	this.commandes = commandes;
}

public Book(long id, String title,
		 String author,
		 double price,
		 String releaseDate,
		 String cover) {
	super();
	this.id = id;
	this.title = title;
	this.author = author;
	this.price = price;
	this.releaseDate = releaseDate;
	this.cover = cover;
}

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getAuthor() {
	return author;
}

public void setAuthor(String author) {
	this.author = author;
}

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}

public String getReleaseDate() {
	return releaseDate;
}

public void setReleaseDate(String releaseDate) {
	this.releaseDate = releaseDate;
}

public String getCover() {
	return cover;
}

public void setCover(String cover) {
	this.cover = cover;
}

@NotBlank(message = "cover")
 @Column(name = "cover")
 private String cover;
 
 public Book() {}
 
 }