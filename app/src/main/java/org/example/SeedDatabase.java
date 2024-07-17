package org.example;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

/** Reload the database with test users. */
@Singleton
public class SeedDatabase {
  @Transactional
  public void seed(@Observes StartupEvent evt) {
    User.deleteAll();
    User.add("Alice Admin", "admin", "admin", "admin");
    User.add("Random User", "user", "user", "user");
  }
}
