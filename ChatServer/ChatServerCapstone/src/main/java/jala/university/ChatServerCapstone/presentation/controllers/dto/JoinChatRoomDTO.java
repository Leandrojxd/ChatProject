package jala.university.ChatServerCapstone.presentation.controllers.dto;

public class JoinChatRoomDTO {

  private String username;
  private String chatRoomName;

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
