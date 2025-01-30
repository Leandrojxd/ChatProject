package jala.university.chatcapstone.model;

public class EncryptedMessageUser {
  private String username;
  private String message;

  public EncryptedMessageUser(){}

  public EncryptedMessageUser(String username, String message) {
    this.username = username;
    this.message = message;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
