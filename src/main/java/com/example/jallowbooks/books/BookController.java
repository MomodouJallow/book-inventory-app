package com.example.jallowbooks.books;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {
    final private BookService bookService;

    @PreAuthorize("permitAll()")
    @GetMapping(path="/search")
    public ResponseEntity<List<TheBook>> searchBooks(
            @RequestParam("searchTerm") String searchTerm
    ){
        List<TheBook> books = bookService.searchBooks(searchTerm);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PreAuthorize("permitAll()")
    @GetMapping(path="/get-all")
    public ResponseEntity<List<TheBook>> getAllBooks(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit
    ){
        List<TheBook> books = bookService.getAllBooks(offset, limit);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<TheBook> createBook(
            @RequestBody CreateBookRequest request
    ) {
        TheBook newBook = bookService.createBook(
                request.getAuthorId(),
                request.getTitle(),
                request.getIsbn(),
                request.getPublicationYear(),
                request.getGenre()
        );
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path="/update/{bookId}")
    public ResponseEntity<TheBook> updateBook(
            @PathVariable("bookId") Long bookId,
            @RequestBody UpdateAuthorRequest request
    ) {
        TheBook updatedBook = bookService.updateBook(
                bookId,
                request.getAuthorId(),
                request.getTitle(),
                request.getIsbn(),
                request.getPublicationYear(),
                request.getGenre());
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path="/get/{bookId}")
    public ResponseEntity<TheBook> getBook(
            @PathVariable("bookId") Long bookId
        ){
        TheBook book= bookService.getBook(bookId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path="/get-by-author/{authorId}")
    public ResponseEntity<List<TheBook>> getBooksByAuthorId(
            @PathVariable("authorId") Long authorId
    ){
        List<TheBook> books = bookService.getBooksByAuthorId(authorId);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path="/delete/{bookId}")
    public ResponseEntity<GeneralBookResponse> deleteBook(
            @PathVariable("bookId") Long bookId
    ){
        bookService.deleteBook(bookId);
        GeneralBookResponse deleteResponse = new GeneralBookResponse();
        deleteResponse.setMessage("TheBook deleted successfully");
        deleteResponse.setStatusCode(200);

        return ResponseEntity.ok(deleteResponse);
    }
}
