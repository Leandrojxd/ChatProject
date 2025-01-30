package jala.university.ChatServerCapstone.dataaccess.models;

import java.util.List;
import org.springframework.data.annotation.Id;

public class ChatMessage {

  @Id
  private String id;
  private List<EncryptedMessageUser> encryptedMessageUserList;
  private String username;

  public ChatMessage(String username,
      List<EncryptedMessageUser> encryptedMessageUserList) {
    this.username = username;
    this.encryptedMessageUserList = encryptedMessageUserList;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<EncryptedMessageUser> getEncryptedMessageUserList() {
    return encryptedMessageUserList;
  }

  public void setEncryptedMessageUserList(
      List<EncryptedMessageUser> encryptedMessageUserList) {
    this.encryptedMessageUserList = encryptedMessageUserList;
  }
}
