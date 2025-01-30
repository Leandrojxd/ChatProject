package jala.university.chatcapstone.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import jala.university.chatcapstone.controller.RequestHandler;
import jala.university.chatcapstone.controller.consts.UrlServices;
import jala.university.chatcapstone.model.ChatRoom;
import jala.university.chatcapstone.model.UserSession;
import java.util.Map;

public class ChatRoomServices {

  private final StringBuilder stringBuilder;
  private final ObjectMapper objectMapper;
  private final RequestHandler requestHandler;

  public ChatRoomServices() {
    this.stringBuilder = new StringBuilder();
    this.requestHandler = new RequestHandler();
    this.objectMapper = new ObjectMapper();
  }

  public String[] getAllUserChatRoomNames(String username) {
    try {
      stringBuilder.setLength(0);
      String url = stringBuilder.append(UrlServices.LOCALHOST).append(UrlServices.CHATROOM)
          .append(UrlServices.BYUSER).toString();
      String resultRequest = requestHandler.get(url, Map.of("username", username));
      String[] chatRoomNames = objectMapper.readValue(resultRequest, String[].class);
      return chatRoomNames;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  public void sharePubKeyIntoChatRoom(String chatRoomName) {
    try {
      stringBuilder.setLength(0);
      String url = stringBuilder.append(UrlServices.LOCALHOST).append(UrlServices.CHATROOM)
          .append(UrlServices.SHAREPUBKEY).toString();
      String jsonBodySharePubKey = String.format(
          "{\"username\":\"%s\",\"publicKey\":\"%s\",\"chatRoomName\":\"%s\"}",
          UserSession.getInstance().getUsername(),
          RSAKeyPairGenerator.getInstance().getPublicKeyBase64(), chatRoomName);
      requestHandler.post(url,jsonBodySharePubKey);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public ChatRoom getChatRoomByName(String chatRoomName) {
    try {
      stringBuilder.setLength(0);
      String url = stringBuilder.append(UrlServices.LOCALHOST).append(UrlServices.CHATROOM)
          .toString();
      String resultRequest = requestHandler.get(url, Map.of("chatRoomName", chatRoomName));
      return objectMapper.readValue(resultRequest, ChatRoom.class);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  public ChatRoom createRoom(String nameChatRoom) {
    try {
      stringBuilder.setLength(0);
      String url = stringBuilder.append(UrlServices.LOCALHOST).append(UrlServices.CHATROOM)
          .toString();
      UserSession userSession = UserSession.getInstance();

      String jsonBodyPostChatRoom = String.format(
          "{\"usernameOwner\":\"%s\",\"nameChatRoom\":\"%s\"}",
          userSession.getUsername(), nameChatRoom);

      String resultRequest = requestHandler.post(url, jsonBodyPostChatRoom);
      return objectMapper.readValue(resultRequest, ChatRoom.class);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  public ChatRoom enterIntoChatRoom(String chatRoomName) {
    try {
      stringBuilder.setLength(0);
      String url = stringBuilder.append(UrlServices.LOCALHOST).append(UrlServices.CHATROOM)
          .toString();
      String username = UserSession.getInstance().getUsername();

      String jsonBodyUpdateChatRoom = String.format("{\"username\":\"%s\",\"chatRoomName\":\"%s\"}",
          username, chatRoomName);
      String resultRequest = requestHandler.put(url, jsonBodyUpdateChatRoom);
      return objectMapper.readValue(resultRequest, ChatRoom.class);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
      return null;
    }
  }

  public void joinChatRoom() {

  }

}
