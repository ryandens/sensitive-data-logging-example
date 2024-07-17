# sensitive-data-logging-example

Demonstrates Pixee's sensitive data logging codemod

The file `app/src/main/java/com/example/UserProfile` contains two log statements that reference the user's password.

The first is this log statement that flagrantly logs the user's password:

```java
logger.infof("user %s authenticated with password %s",user.username(),user.

password());
```

The second is this log statement that references the user's password in an expression but ultimately does not log it:

```java
logger.infof(
    "is password compromised? %s",
    compromisedPasswordChecker.isPasswordCompromised(user.password()));
```

Pixee's sensitive data logging find-and-fix codemod will find and fix the first log statement, but not the second. It
uses OpenAI to understand the code and determine whether the password is logged or not.
