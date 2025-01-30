package jala.university.chatcapstone.view;

import jala.university.chatcapstone.model.ChatMessage;
import jala.university.chatcapstone.model.ChatRoom;
import jala.university.chatcapstone.model.UserSession;
import jala.university.chatcapstone.services.ChatRoomServices;
import jala.university.chatcapstone.services.ChatSubscriberService;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class HomePageController {

  private final ChatRoomServices chatRoomServices;
  private final ChatSubscriberService chatSubscriberService;

  public HomePageController() {
    this.chatRoomServices = new ChatRoomServices();
    this.chatSubscriberService = new ChatSubscriberService();
  }

  @FXML
  private ListView<String> groupsListView;

  @FXML
  private TextField joinCodeTextField;

  @FXML
  private TextField chatRoomNameTextField;

  @FXML
  public void initialize() {
    loadChatRooms();
  }

  @FXML
  private void handleViewGroup() {
    String chatRoomName = groupsListView.getSelectionModel().getSelectedItem();
    if (chatRoomName != null && !chatRoomName.isEmpty()) {

      ChatMessage[] listChatMessage = chatRoomServices.getChatRoomByName(chatRoomName)
          .getChatMessage();
      chatSubscriberService.subscribeToTopic(chatRoomName);
      NavigationManager.getInstance()
          .navigateTo(PathViews.ChatPage, (ChatPageController controller) -> {
            controller.setListChatMessage(listChatMessage);
            controller.setChatRoomName(chatRoomName);
            controller.setChatSubscriberService(chatSubscriberService);
          });
    }
  }

  @FXML
  private void handleJoinGroup() {
    String chatRoomName = joinCodeTextField.getText();
    ChatRoom chatRoom = chatRoomServices.enterIntoChatRoom(chatRoomName);

    if (chatRoom != null && !Objects.equals(chatRoomName, "")) {
      chatSubscriberService.subscribeToTopic(chatRoomName);
      chatRoomServices.sharePubKeyIntoChatRoom(chatRoomName);
      loadChatRooms();
    }
  }

  @FXML
  private void handleCreateRoom() {
    String chatRoomName = chatRoomNameTextField.getText();
    ChatRoom creationChatRoom = chatRoomServices.createRoom(chatRoomName);
    if (creationChatRoom != null && !Objects.equals(chatRoomName, "")) {
      chatRoomServices.sharePubKeyIntoChatRoom(chatRoomName);
      loadChatRooms();
    }
  }

  private void loadChatRooms() {
    groupsListView.getItems().removeAll();

    String[] chatRoomNames = chatRoomServices.getAllUserChatRoomNames(
        UserSession.getInstance().getUsername());
    for (String chatRoomName : chatRoomNames) {
      if(groupsListView.getItems().stream().noneMatch((roomName) -> Objects.equals(roomName,
          chatRoomName))){
        groupsListView.getItems().add(chatRoomName);
      }
    }
  }
}
