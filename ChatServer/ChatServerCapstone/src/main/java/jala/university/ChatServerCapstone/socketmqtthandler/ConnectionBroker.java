package jala.university.ChatServerCapstone.socketmqtthandler;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class ConnectionBroker {

  private static ConnectionBroker instance;
  private final MqttClient client;

  private ConnectionBroker() {
    try {
      client = new MqttClient(
          "tcp://localhost:1883",
          MqttClient.generateClientId(),
          new MemoryPersistence()
      );

      MqttConnectOptions options = new MqttConnectOptions();
      options.setUserName("mqtt");
      options.setPassword("Admin123".toCharArray());

      client.connect(options);

    } catch (MqttException e) {
      throw new RuntimeException("Error connecting to the broker", e);
    }
  }

  public static ConnectionBroker getInstance() {
    if (instance == null) {
      instance = new ConnectionBroker();
    }
    return instance;
  }

  public void publish(String chatRoomName, String messageContent) {
    try {
      if (!client.isConnected()) {
        System.out.println("Client is not connected, reconnecting...");
        client.reconnect();
      }

      MqttMessage message = new MqttMessage(messageContent.getBytes());
      message.setQos(2);

      client.publish(chatRoomName, message);
    } catch (MqttException e) {
      throw new RuntimeException("Error publishing message to chat room: " + chatRoomName, e);
    }
  }
}
