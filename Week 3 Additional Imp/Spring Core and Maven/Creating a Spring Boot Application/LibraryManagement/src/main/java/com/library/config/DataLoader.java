package com.library.config;

import com.library.model.Book;
import com.library.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(BookRepository repo) {
        return args -> {
            repo.save(new Book(null, "Clean Code", "Robert C. Martin", 450));
            repo.save(new Book(null, "Effective Java", "Joshua Bloch", 550));
        };
    }
}
