package jala.university.ChatServerCapstone.socketmqtthandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jala.university.ChatServerCapstone.dataaccess.models.EncryptedMessageUser;
import jala.university.ChatServerCapstone.presentation.controllers.dto.RegisterMessageDTO;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ChatRoomServiceRequest {

  private final HttpClient httpClient;
  private final ObjectMapper objectMapper;

  public ChatRoomServiceRequest() {
    httpClient = HttpClient.newHttpClient();
    objectMapper = new ObjectMapper();
  }

  public void updateChatMessage(String username, String chatRoomName,
      List<EncryptedMessageUser> chatMessage) {
    try {
      String requestBody = objectMapper.writeValueAsString(
          new RegisterMessageDTO(username, chatMessage, chatRoomName));

      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create("http://localhost:8080/chatRoom/addMessage"))
          .header("Content-Type", "application/json")
          .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
          .build();

      HttpResponse<String> response = httpClient.send(request,
          HttpResponse.BodyHandlers.ofString());
      System.out.println(response);
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}
