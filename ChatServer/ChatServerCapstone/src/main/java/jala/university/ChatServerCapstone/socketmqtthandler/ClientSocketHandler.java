package jala.university.ChatServerCapstone.socketmqtthandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jala.university.ChatServerCapstone.dataaccess.models.EncryptedMessageUser;
import jala.university.ChatServerCapstone.dataaccess.models.PublishMessageMqtt;
import jala.university.ChatServerCapstone.dataaccess.models.SocketChatMessage;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class ClientSocketHandler extends Thread {

  String socketMessage;
  DataInputStream inputStream = null;
  DataOutputStream outputStream = null;
  Socket socketClient;
  ObjectMapper objectMapper;
  ChatRoomServiceRequest chatRoomServiceRequest;

  public ClientSocketHandler(Socket socketClient) {
    this.socketClient = socketClient;
    this.objectMapper = new ObjectMapper();
    this.chatRoomServiceRequest = new ChatRoomServiceRequest();
  }

  public void run() {
    try {
      inputStream = new DataInputStream(new BufferedInputStream(socketClient.getInputStream()));
      outputStream = new DataOutputStream(socketClient.getOutputStream());
    } catch (IOException ioException) {
      System.out.println(ioException.getMessage());
    }

    try {
      socketMessage = inputStream.readUTF();
      while (socketMessage.compareTo("QUIT") != 0 || socketClient.isClosed()) {

        SocketChatMessage socketChatMessage = objectMapper.readValue(socketMessage,
            SocketChatMessage.class);
        String chatRoomName = socketChatMessage.getChatRoomName();
        List<EncryptedMessageUser> encryptedMessageUserList = socketChatMessage.getEncryptedMessageUsers();
        String username = socketChatMessage.getUsername();
        String hashMessage = socketChatMessage.getHashMessage();
        chatRoomServiceRequest.updateChatMessage(username, chatRoomName, encryptedMessageUserList);

        Arrays.stream(encryptedMessageUserList.toArray()).sequential().forEach(System.out::println);
        PublishMessageMqtt publishMessageMqtt = new PublishMessageMqtt(username,
            encryptedMessageUserList,hashMessage);

        ConnectionBroker.getInstance()
            .publish(chatRoomName, objectMapper.writeValueAsString(publishMessageMqtt));
        socketMessage = inputStream.readUTF();
      }
    } catch (IOException ioException) {
      System.out.println(ioException.getMessage());
      socketMessage = this.getName();
      System.out.println("IO Error/Client" + socketMessage + "terminated abruptly.");
    }
  }
}
