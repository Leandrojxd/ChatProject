package jala.university.chatcapstone.view;

import jala.university.chatcapstone.model.UserSession;
import jala.university.chatcapstone.services.AuthServices;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginPageController {

  private final AuthServices authServices;

  public LoginPageController() {
    this.authServices = AuthServices.getInstance();
  }


  @FXML
  private TextField usernameField;

  @FXML
  private PasswordField passwordField;

  @FXML
  private Label errorLabel;

  @FXML
  private void handleLoginButton() {
    String username = usernameField.getText();
    String password = passwordField.getText();

    if (isValidCredentials(username, password)) {
      UserSession.createInstance(username, password);
      NavigationManager.getInstance().navigateTo(PathViews.HomePage);
    } else {
      errorLabel.setText("Invalid username or password.");
    }
  }

  @FXML
  private void handleBackButton() {
    NavigationManager.getInstance().navigateTo(PathViews.ChatApplicationView);
  }

  private boolean isValidCredentials(String username, String password) {
    String resultLogin = authServices.login(username, password);
    return resultLogin.equals("Login Success");
  }
}
