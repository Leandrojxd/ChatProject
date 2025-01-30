package jala.university.chatcapstone.model;

import java.util.List;

public class ChatRoom {

  private String id;

  private String name;

  private String userIdOwner;

  private String[] members;

  private ChatMessage[] chatMessage;

  private PubKeyUser[] pubKeyUserList;

  public PubKeyUser[] getPubKeyUserList() {
    return pubKeyUserList;
  }

  public void setPubKeyUserList(PubKeyUser[] pubKeyUserList) {
    this.pubKeyUserList = pubKeyUserList;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ChatMessage[] getChatMessage() {
    return chatMessage;
  }

  public void setChatMessage(ChatMessage[] chatMessage) {
    this.chatMessage = chatMessage;
  }

  public String getUserIdOwner() {
    return userIdOwner;
  }

  public void setUserIdOwner(String userIdOwner) {
    this.userIdOwner = userIdOwner;
  }

  public String[] getMembers() {
    return members;
  }

  public void setMembers(String[] members) {
    this.members = members;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}