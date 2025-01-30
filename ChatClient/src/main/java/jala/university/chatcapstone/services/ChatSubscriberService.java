package jala.university.chatcapstone.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import jala.university.chatcapstone.model.EncryptedMessageUser;
import jala.university.chatcapstone.model.PublishMessageMqtt;
import jala.university.chatcapstone.model.UserSession;
import java.util.List;
import java.util.Objects;
import javafx.application.Platform;
import javafx.scene.control.ListView;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class ChatSubscriberService {

  private ListView<String> messageListView;
  private MqttClient client;
  private ObjectMapper objectMapper;
  private HashGenerator hashGenerator = new HashGenerator();
  public ChatSubscriberService() {
    try {
      client = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId(),
          new MemoryPersistence());
      objectMapper = new ObjectMapper();
      MqttConnectOptions options = new MqttConnectOptions();
      options.setUserName("mqtt");
      options.setPassword("Admin123".toCharArray());
      client.connect(options);
    } catch (MqttException e) {
      e.printStackTrace();
    }
  }

  public void subscribeToTopic(String topicName) {
    try {
      client.subscribe(topicName, (topic, message) -> {
        try {
          PublishMessageMqtt publishMessageMqtt = objectMapper
              .readValue(message.getPayload(), PublishMessageMqtt.class);
          String usernameSession = UserSession.getInstance().getUsername();
          String decryptedMessage = "";
          if (!Objects.equals(publishMessageMqtt.getUsername(),
              usernameSession)) {
            List<EncryptedMessageUser> encryptedMessageUsers = publishMessageMqtt.getEncryptedMessageUserList();
            for (EncryptedMessageUser encryptedMessageUser: encryptedMessageUsers){
              if(Objects.equals(encryptedMessageUser.getUsername(), usernameSession)){
                String encryptedMessage = encryptedMessageUser.getMessage();
                decryptedMessage = RSAKeyPairGenerator.getInstance().decrypt(encryptedMessage);
              }
            }

            String finalDecryptedMessage = decryptedMessage;
            String originalHashMessage = publishMessageMqtt.getHashMessage();
            String verifierHashMessage = hashGenerator.generateSHA256Hash(finalDecryptedMessage);
            if(originalHashMessage.equals(verifierHashMessage)){
              Platform.runLater(() -> {
                messageListView.getItems()
                    .add(publishMessageMqtt.getUsername() + ": " + finalDecryptedMessage);
              });
            }else{
              Platform.runLater(() -> {
                messageListView.getItems()
                    .add("Message Corrupt or Changed please change your password");
              });
            }
          }

        } catch (Exception e) {
          e.printStackTrace();
        }
      });
    } catch (MqttException e) {
      e.printStackTrace();
    }
  }

  public void startListening() {
    new Thread(() -> {
      try {
        while (true) {
          Thread.sleep(1000);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
  }

  public void setMessageListView(ListView<String> messageListView) {
    this.messageListView = messageListView;
  }
}
