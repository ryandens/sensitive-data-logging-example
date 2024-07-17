package org.example.security;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
class NotYetImplementedCompromisedPasswordChecker implements CompromisedPasswordChecker {
  @Override
  public boolean isPasswordCompromised(final String password) {
    return false;
  }
}
