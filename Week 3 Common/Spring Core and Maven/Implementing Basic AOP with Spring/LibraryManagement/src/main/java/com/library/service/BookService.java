package com.library.service;

import com.library.helper.BookHelper;
import com.library.repository.BookRepository;

public class BookService {
    private BookHelper bookHelper;          
    private BookRepository bookRepository;  

    public BookService(BookHelper bookHelper) {
        this.bookHelper = bookHelper;
    }
    
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void processBooks() {
        System.out.println("Inside BookService:");
        bookHelper.assist();
        bookRepository.displayBooks();
    }
}
