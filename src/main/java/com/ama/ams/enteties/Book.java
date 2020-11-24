package com.ama.ams.enteties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
@Entity
public class Book {
@Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private int id;
 @NotBlank(message = "title is mandatory")
 @Column(name = "title")
 private String title;

 @NotBlank(message = "author is mandatory")
 @Column(name = "author")
 private String author;

 @NotBlank(message = "price is mandatory")
 @Column(name = "price")
 private double price;
 
 @NotBlank(message = "release date is mandatory")
 @Column(name = "releaseDate")
 private String releaseDate;
 
 public Book(int id, String title,
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

public int getId() {
	return id;
}

public void setId(int id) {
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