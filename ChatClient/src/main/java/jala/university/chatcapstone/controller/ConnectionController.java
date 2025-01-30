package jala.university.chatcapstone.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jala.university.chatcapstone.model.EncryptedMessageUser;
import jala.university.chatcapstone.model.PubKeyUser;
import jala.university.chatcapstone.model.SocketChatMessage;
import jala.university.chatcapstone.model.UserSession;
import jala.university.chatcapstone.services.ChatRoomServices;
import jala.university.chatcapstone.services.HashGenerator;
import jala.university.chatcapstone.services.RSAKeyPairGenerator;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.application.Platform;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ConnectionController {

  private final String serverAddress = "localhost";
  private final int portConnection = 5000;
  private Socket socketConnection;
  private DataInputStream dataInputStreamServer;
  private DataOutputStream dataOutputStream;
  private final ObjectMapper objectMapper;
  private final ChatRoomServices chatRoomServices;
  private HashGenerator hashGenerator;
  public ConnectionController() {
    objectMapper = new ObjectMapper();
    this.chatRoomServices = new ChatRoomServices();
    this.hashGenerator = new HashGenerator();
  }

  public void connectServer() {
    try {
      socketConnection = new Socket(serverAddress, portConnection);
      dataOutputStream = new DataOutputStream(socketConnection.getOutputStream());
      dataInputStreamServer = new DataInputStream(socketConnection.getInputStream());
    } catch (IOException exception) {
      System.out.println(exception.getMessage());
    }
  }

  public void listenServer(ListView displayListView) {
    Thread listenerThreadServer = new Thread(() -> {
      try {
        String serverMessage;
        while ((serverMessage = dataInputStreamServer.readUTF()) != null) {
          String finalServerMessage = serverMessage;
          Platform.runLater(() -> {
            displayListView.getItems().add("Message from the server: " + finalServerMessage);
          });
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
    listenerThreadServer.start();
  }

  public void sendMessage(TextField textFieldInputMessage, String chatRoomName) {
    try {
      String message = textFieldInputMessage.getText();
      String username = UserSession.getInstance().getUsername();

      PubKeyUser[] pubKeyUsers = chatRoomServices.getChatRoomByName(chatRoomName).getPubKeyUserList();
      String hashMessage = hashGenerator.generateSHA256Hash(message);
      List<EncryptedMessageUser> encryptedMessageUsers = new ArrayList<>();

      for (PubKeyUser pubKeyUser :  pubKeyUsers){
          String publicKey = pubKeyUser.getPublicKey();
          String encryptedMessage = RSAKeyPairGenerator.getInstance().encrypt(message,publicKey);
          String memberGroupUserName = pubKeyUser.getUsername();
          EncryptedMessageUser encryptedMessageUser = new EncryptedMessageUser(memberGroupUserName,encryptedMessage);
          encryptedMessageUsers.add(encryptedMessageUser);
      }

      String socketChatMessageJson = objectMapper
          .writeValueAsString(new SocketChatMessage(username, encryptedMessageUsers, chatRoomName,hashMessage));

      dataOutputStream.writeUTF(socketChatMessageJson);
      textFieldInputMessage.setText("");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
