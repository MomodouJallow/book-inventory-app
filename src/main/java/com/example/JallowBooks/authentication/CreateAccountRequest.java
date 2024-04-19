package com.example.JallowBooks.authentication;

import com.example.JallowBooks.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountRequest {

  private String username;
  private String email;
  private String password;
  private Role role;
}
