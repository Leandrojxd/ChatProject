package jala.university.ChatServerCapstone.socketmqtthandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SocketConnectionManager {

  private final int portConnection = 5000;
  private static final ArrayList<ClientSocketHandler> clientSocketHandlers = new ArrayList<>();

  public void start() {
    try {
      ServerSocket socketServer = new ServerSocket(portConnection);

      while (true) {
        try {
          Socket clientSocket = socketServer.accept();
          ClientSocketHandler clientSocketHandler = new ClientSocketHandler(clientSocket);
          clientSocketHandlers.add(clientSocketHandler);
          clientSocketHandler.start();
        } catch (Exception e) {
          e.printStackTrace();
          System.out.println("connection error");
        }
      }
    } catch (Error | IOException error) {
      error.printStackTrace();
    }
  }
}