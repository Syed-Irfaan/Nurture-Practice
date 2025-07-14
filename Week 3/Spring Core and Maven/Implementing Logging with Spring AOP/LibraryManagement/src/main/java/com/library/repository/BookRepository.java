package com.library.repository;

import java.util.Arrays;
import java.util.List;

public class BookRepository {
    public List<String> findAllTitles() {
        return Arrays.asList("Clean Code", "Design Patterns", "Spring in Action");
    }
}
