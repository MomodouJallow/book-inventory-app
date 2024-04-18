package com.example.jallowbooks.theAuthors;

import com.example.jallowbooks.errors.BadRequest;
import com.example.jallowbooks.errors.NotFound;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {


    private final AuthorRepository authorRepository;

    @Transactional
    public Author updateAuthor(Long authorId, String authorName, String bio) {
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> new NotFound("Author with id " + authorId + " does not exists")
        );

        if (bio != null && bio.length()>0 && !Objects.equals(author.getBio(), bio)) {
            author.setBio(bio);
            author.setUpdatedAt(LocalDateTime.now());
            System.out.println("Finished setting bio field");
        }

        if (authorName != null && authorName.length()>0 && !Objects.equals(author.getAuthorName(), authorName)) {
            Optional<Author> authorOptional = authorRepository.findByAuthorName(authorName);
            if (authorOptional.isPresent()) {
                throw new BadRequest("Can't Update to already added name");
            }
            author.setAuthorName(authorName);
            author.setUpdatedAt(LocalDateTime.now());
        }
        authorRepository.save(author);

        return author;
    }

    public Author getAuthor(Long authorId) {
        return authorRepository.findById(authorId).orElseThrow(
                () -> new NotFound("Author with id " + authorId + " does not exists")
        );
    }


    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author createAuthor( String authorName, String bio) {
        Optional<Author> author = authorRepository.findByAuthorName(authorName);

        if (author.isPresent()) {
            throw new BadRequest("Author of provided name already added");
        }
        Author newAuthor = Author.builder()
                .authorName(authorName)
                .bio(bio)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return authorRepository.save(newAuthor);
    }

    public void deleteAuthor(Long authorId) {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            authorRepository.delete(author);
        } else {
            throw new NotFound("Author with id " + authorId + " not found");
        }
    }

}

