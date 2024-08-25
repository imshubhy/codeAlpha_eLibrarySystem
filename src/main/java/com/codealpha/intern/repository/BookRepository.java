package com.codealpha.intern.repository;

import com.codealpha.intern.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {}
