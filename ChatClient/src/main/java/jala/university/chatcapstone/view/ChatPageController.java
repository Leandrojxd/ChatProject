package jala.university.chatcapstone.view;

import jala.university.chatcapstone.controller.ConnectionController;
import jala.university.chatcapstone.model.ChatMessage;
import jala.university.chatcapstone.model.EncryptedMessageUser;
import jala.university.chatcapstone.model.UserSession;
import jala.university.chatcapstone.services.ChatSubscriberService;
import jala.university.chatcapstone.services.RSAKeyPairGenerator;
import java.util.List;
import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ChatPageController {

  private final ConnectionController connectionController;
  private String chatRoomName;
  private ChatSubscriberService chatSubscriberService;

  private final ObservableList<ChatMessage> listChatMessage = FXCollections.observableArrayList();

  @FXML
  public ListView<String> messageListView;

  @FXML
  public Button sendButton;

  @FXML
  public TextField messageInput;

  public ChatPageController() {
    connectionController = new ConnectionController();
    connectionController.connectServer();

    listChatMessage.addListener((ListChangeListener<ChatMessage>) change -> {
      while (change.next()) {
        if (change.wasAdded()) {
          handleChatMessageAdded(change.getAddedSubList());
        }
      }
    });
  }

  @FXML
  public void initialize() {
    connectionController.listenServer(messageListView);
  }

  @FXML
  public void handleSendMessage(ActionEvent actionEvent) {
    String message = messageInput.getText();
    if (!message.isEmpty()) {
      connectionController.sendMessage(messageInput, chatRoomName);
      messageListView.getItems().add(UserSession.getInstance().getUsername() + ":" + message);
      messageInput.clear();
    }
  }

  private void handleChatMessageAdded(java.util.List<? extends ChatMessage> addedMessages) {
    for (ChatMessage chatMessage : addedMessages) {
      List<EncryptedMessageUser> encryptedMessageUserList = chatMessage.getEncryptedMessageUserList();
      String messageFromUsername = chatMessage.getUsername();
      String decryptedMessage = "";
      String username = UserSession.getInstance().getUsername();
      for (EncryptedMessageUser encryptedMessageUser: encryptedMessageUserList){
        if(Objects.equals(encryptedMessageUser.getUsername(), username)){
          decryptedMessage = RSAKeyPairGenerator.getInstance().decrypt(encryptedMessageUser.getMessage());
        }
      }
      if (!Objects.equals(decryptedMessage, "")) {
        messageListView.getItems()
            .add(messageFromUsername + ": " + decryptedMessage);
      }
    }
  }


  public String getChatRoomName() {
    return chatRoomName;
  }

  public void setChatRoomName(String chatRoomName) {
    this.chatRoomName = chatRoomName;
  }

  public void setListChatMessage(ChatMessage[] listChatMessage) {
    this.listChatMessage.setAll(listChatMessage);
  }

  public void setChatSubscriberService(ChatSubscriberService chatSubscriberService) {
    this.chatSubscriberService = chatSubscriberService;
    this.chatSubscriberService.setMessageListView(messageListView);
  }
}
