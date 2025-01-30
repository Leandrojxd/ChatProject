package jala.university.ChatServerCapstone.presentation.controllers.dto;

public class PubKeyUserDTO {

  private String username;
  private String publicKey;
  private String chatRoomName;

  public PubKeyUserDTO(String username, String publicKey, String chatRoomName) {
    this.username = username;
    this.publicKey = publicKey;
    this.chatRoomName = chatRoomName;
  }

  public String getChatRoomName() {
    return chatRoomName;
  }

  public void setChatRoomName(String chatRoomName) {
    this.chatRoomName = chatRoomName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }
}
