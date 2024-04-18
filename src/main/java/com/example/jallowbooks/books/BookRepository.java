package com.example.jallowbooks.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<TheBook, Long> {
    List<TheBook> findByAuthorId(Long authorId);
    Optional<TheBook> findByTitle(String title);
    @Query("SELECT b FROM TheBook b WHERE lower(b.title) LIKE %:searchTerm% OR lower(b.genre) LIKE %:searchTerm%")
    List<TheBook> findByTitleOrGenre(@Param("searchTerm") String searchTerm);

}


