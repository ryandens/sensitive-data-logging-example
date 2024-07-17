package org.example;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_profiles")
@UserDefinition
public class User extends PanacheEntity {

  /**
   * Adds a new user to the database
   *
   * @param username the username
   * @param password the unencrypted password (it is encrypted with bcrypt)
   * @param role the comma-separated roles
   */
  public static void add(String name, String username, String password, String role) {
    User user = new User();
    user.name = name;
    user.username = username;
    user.password = BcryptUtil.bcryptHash(password);
    user.role = role;
    user.persist();
  }

  public static User findByUsername(String username) {
    return find("username", username).firstResult();
  }

  private String name;
  @Username private String username;
  @Password private String password;
  @Roles private String role;

  public String name() {
    return name;
  }

  public String username() {
    return username;
  }

  public String password() {
    return password;
  }

  public String role() {
    return role;
  }
}
