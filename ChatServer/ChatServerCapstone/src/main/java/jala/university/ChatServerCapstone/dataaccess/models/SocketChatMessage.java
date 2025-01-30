package jala.university.ChatServerCapstone.dataaccess.models;

import java.util.List;

public class SocketChatMessage {

  private String username;
  private List<EncryptedMessageUser> encryptedMessageUsers;
  private String chatRoomName;
  private String hashMessage;

  public String getHashMessage() {
    return hashMessage;
  }

  public void setHashMessage(String hashMessage) {
    this.hashMessage = hashMessage;
  }

  public List<EncryptedMessageUser> getEncryptedMessageUsers() {
    return encryptedMessageUsers;
  }

  public void setEncryptedMessageUsers(
      List<EncryptedMessageUser> encryptedMessageUsers) {
    this.encryptedMessageUsers = encryptedMessageUsers;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getChatRoomName() {
    return chatRoomName;
  }

  public void setChatRoomName(String chatRoomName) {
    this.chatRoomName = chatRoomName;
  }
}
