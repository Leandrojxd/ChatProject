package jala.university.chatcapstone.model;

public class UserSession {

  private final String username;
  private final String password;
  private static UserSession instance;

  private UserSession(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public static void createInstance(String username, String password) {
    if (instance == null) {
      instance = new UserSession(username, password);
    }
  }

  public static UserSession getInstance() {
    return instance;
  }

  public String getUsername() {
    return username;
  }
}
