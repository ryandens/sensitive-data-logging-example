package org.example;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;
import org.example.security.CompromisedPasswordChecker;
import org.jboss.logging.Logger;

/** REST resource that provides access to user profile information. */
@Path("/api/users")
public class UserProfileResource {

  @Inject CompromisedPasswordChecker compromisedPasswordChecker;
  @Inject Logger logger;

  /** Get the profile of the authenticated user. */
  @GET
  @RolesAllowed("user")
  @Path("/me")
  @Produces(APPLICATION_JSON)
  public UserProfile me(@Context SecurityContext securityContext) {
    final var username = securityContext.getUserPrincipal().getName();
    final var user = User.findByUsername(username);
    notifyUserIfPasswordIsCompromised(user);
    return new UserProfile(user.name(), user.username());
  }

  /** Notify the user if their password is compromised by integrating with a third-party service. */
  private void notifyUserIfPasswordIsCompromised(final User user) {
    logger.infof(
        "is password compromised? %s",
        compromisedPasswordChecker.isPasswordCompromised(user.password()));
    // TODO implement notification logic
  }
}
