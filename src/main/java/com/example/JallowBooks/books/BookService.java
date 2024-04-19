package com.example.JallowBooks.books;

import com.example.JallowBooks.errors.BadRequest;
import com.example.JallowBooks.errors.NotFound;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public TheBook createBook(Long authorId, String title, String isbn, String publicationYear, String genre) {
        Optional<TheBook> book = bookRepository.findByTitle(title);

        if (book.isPresent()) {
            throw new BadRequest("TheBook of provided title is already added");
        }
        TheBook newBook = TheBook.builder()
                .authorId(authorId)
                .title(title)
                .isbn(isbn)
                .publicationYear(publicationYear)
                .genre(genre)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return bookRepository.save(newBook);
    }

    @Transactional
    public TheBook updateBook(Long bookId, Long authorId, String title, String isbn, String publicationYear, String genre) {
        TheBook book = bookRepository.findById(bookId).orElseThrow(
                () -> new NotFound("TheBook with id " + bookId + " does not exists")
        );

        if (authorId != null && authorId>0 && !Objects.equals(book.getAuthorId(), authorId)) {
            book.setAuthorId(authorId);
            book.setUpdatedAt(LocalDateTime.now());
        }

        if (title != null && title.length()>0 && !Objects.equals(book.getTitle(), title)) {
            Optional<TheBook> bookOptional = bookRepository.findByTitle(title);
            if (bookOptional.isPresent()) {
                throw new BadRequest("Can't Update to already added title");
            }
            book.setTitle(title);
            book.setUpdatedAt(LocalDateTime.now());
        }

        if (isbn != null && isbn.length()>0 && !Objects.equals(book.getIsbn(), isbn)) {
            book.setIsbn(isbn);
            book.setUpdatedAt(LocalDateTime.now());
        }

        if (publicationYear != null && publicationYear.length()>0 && !Objects.equals(book.getPublicationYear(), publicationYear)) {
            book.setPublicationYear(publicationYear);
            book.setUpdatedAt(LocalDateTime.now());
        }

        if (genre != null && genre.length()>0 && !Objects.equals(book.getGenre(), genre)) {
            book.setGenre(publicationYear);
            book.setUpdatedAt(LocalDateTime.now());
        }

        bookRepository.save(book);
        return book;
    }

    public TheBook getBook(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(
            () -> new NotFound("TheBook with id " + bookId + " does not exists")
        );
    }



    public List<TheBook> getAllBooks(int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit, Sort.by("id").ascending());

        return bookRepository.findAll(pageable).getContent();
    }

    public List<TheBook> getBooksByAuthorId(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }


    public List<TheBook> searchBooks(String searchTerm) {
        return bookRepository.findByTitleOrGenre(searchTerm.toLowerCase());
    }

    public void deleteBook(Long bookId) {
        Optional<TheBook> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            TheBook book = optionalBook.get();
            bookRepository.delete(book);
        } else {
            throw new NotFound("TheBook with id " + bookId + " not found");
        }
    }

}

