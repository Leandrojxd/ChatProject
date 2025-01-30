package jala.university.chatcapstone.model;

import jala.university.chatcapstone.model.EncryptedMessageUser;
import java.util.List;

public class ChatMessage {


  private String id;
  private List<EncryptedMessageUser> encryptedMessageUserList;
  private String username;

  public ChatMessage(){}

  public ChatMessage(String username,
      List<EncryptedMessageUser> encryptedMessageUserList) {
    this.username = username;
    this.encryptedMessageUserList = encryptedMessageUserList;
  }

  public List<EncryptedMessageUser> getEncryptedMessageUserList() {
    return encryptedMessageUserList;
  }

  public void setEncryptedMessageUserList(
      List<EncryptedMessageUser> encryptedMessageUserList) {
    this.encryptedMessageUserList = encryptedMessageUserList;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }



  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
