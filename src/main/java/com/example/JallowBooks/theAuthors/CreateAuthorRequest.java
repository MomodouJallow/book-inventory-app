package com.example.JallowBooks.theAuthors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAuthorRequest {
    private String authorName;
    private String bio;
}

