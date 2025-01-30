package jala.university.ChatServerCapstone.presentation.controllers.dto;

import jala.university.ChatServerCapstone.dataaccess.models.EncryptedMessageUser;
import java.util.List;

public class RegisterMessageDTO {

  private String username;
  private String chatRoomName;
  private List<EncryptedMessageUser> encryptedMessageUserList;

  public RegisterMessageDTO(String username, List<EncryptedMessageUser> encryptedMessageUserList,
      String chatRoomName) {
    this.username = username;
    this.encryptedMessageUserList = encryptedMessageUserList;
    this.chatRoomName = chatRoomName;
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

  public String getChatRoomName() {
    return chatRoomName;
  }

  public void setChatRoomName(String chatRoomName) {
    this.chatRoomName = chatRoomName;
  }
}
