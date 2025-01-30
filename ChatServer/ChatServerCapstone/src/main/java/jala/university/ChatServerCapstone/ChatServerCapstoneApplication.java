package jala.university.ChatServerCapstone;

import jala.university.ChatServerCapstone.socketmqtthandler.SocketConnectionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatServerCapstoneApplication {

  public static void main(String[] args) {

    Thread sprintBootThreadAuth = new Thread(() -> {
      SpringApplication.run(ChatServerCapstoneApplication.class, args);
    });
    sprintBootThreadAuth.setDaemon(false);
    sprintBootThreadAuth.start();

    SocketConnectionManager socketConnectionManager = new SocketConnectionManager();
    socketConnectionManager.start();
  }

}
