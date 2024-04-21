package com.example.jallowbooks.authentication;

import com.example.jallowbooks.user.Role;
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
