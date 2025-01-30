package jala.university.chatcapstone.view;

import jala.university.chatcapstone.model.UserSession;
import jala.university.chatcapstone.services.AuthServices;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterPageController {

  private final AuthServices authServices;

  public RegisterPageController() {
    authServices = AuthServices.getInstance();
  }

  @FXML
  private Label messageLabel;
  @FXML
  private TextField usernameField;

  @FXML
  private PasswordField passwordField;

  @FXML
  private Button backButton;

  @FXML
  private Button registerButton;

  @FXML
  private void handleBackAction() {
    NavigationManager.getInstance().navigateTo(PathViews.ChatApplicationView);
  }

  @FXML
  private void handleRegisterAction() {

    String username = usernameField.getText();
    String password = passwordField.getText();

    if (username.isEmpty() || password.isEmpty()) {
      return;
    }
    String response = authServices.register(username, password);
    messageLabel.setText(response);
    if (!Objects.equals(response, "Username Already exists")) {
      UserSession.createInstance(username, password);
      NavigationManager.getInstance().navigateTo(PathViews.HomePage);
    }
    System.out.println(response);
  }
}
