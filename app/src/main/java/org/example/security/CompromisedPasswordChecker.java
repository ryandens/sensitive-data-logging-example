package org.example.security;

public interface CompromisedPasswordChecker {
  boolean isPasswordCompromised(String password);
}
