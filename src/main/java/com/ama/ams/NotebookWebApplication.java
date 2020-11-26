package com.ama.ams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ama.ams.controllers.BookController;
import java.io.File;

@SpringBootApplication
public class NotebookWebApplication {

	public static void main(String[] args) {
		new File(BookController.uploadDirectory).mkdir();
		SpringApplication.run(NotebookWebApplication.class, args);
	}

}
