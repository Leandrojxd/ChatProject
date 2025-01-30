package jala.university.ChatServerCapstone.dataaccess.models;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("ChatRoom")
public class ChatRoom {

  @Id
  private String id;

  private String name;

  private String userIdOwner;

  private List<String> members;

  private List<ChatMessage> chatMessage;

  private List<PubKeyUser> pubKeyUserList;

  public ChatRoom(String name, String userIdOwner) {
    this.name = name;
    this.userIdOwner = userIdOwner;
    this.members = new ArrayList<>();
    members.add(userIdOwner);
    chatMessage = new ArrayList<>();
    pubKeyUserList = new ArrayList<>();
  }

  public List<PubKeyUser> getPubKeyUserList() {
    return pubKeyUserList;
  }

  public void setPubKeyUserList(
      List<PubKeyUser> pubKeyUserList) {
    this.pubKeyUserList = pubKeyUserList;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getUserIdOwner() {
    return userIdOwner;
  }

  public List<String> getMembers() {
    return members;
  }

  public List<ChatMessage> getChatMessage() {
    return chatMessage;
  }
}
