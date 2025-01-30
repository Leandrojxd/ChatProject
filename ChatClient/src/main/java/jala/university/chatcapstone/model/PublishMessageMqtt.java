package jala.university.chatcapstone.model;

import jala.university.chatcapstone.model.EncryptedMessageUser;
import java.util.List;

public class PublishMessageMqtt {
  private String username;
  private List<EncryptedMessageUser> encryptedMessageUserList;
  private String hashMessage;

  public String getHashMessage() {
    return hashMessage;
  }

  public void setHashMessage(String hashMessage) {
    this.hashMessage = hashMessage;
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
