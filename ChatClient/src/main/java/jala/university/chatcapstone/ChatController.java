package jala.university.chatcapstone;

import jala.university.chatcapstone.view.NavigationManager;
import jala.university.chatcapstone.view.PathViews;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ChatController {


  @FXML
  private void handleRegisterButtonAction(ActionEvent event) throws IOException {
    NavigationManager.getInstance().navigateTo(PathViews.RegisterPage);
  }

  @FXML
  private void handleLoginButtonAction(ActionEvent event) throws IOException {
    NavigationManager.getInstance().navigateTo(PathViews.LoginPage);
  }
}
