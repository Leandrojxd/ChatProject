package jala.university.chatcapstone;

import jala.university.chatcapstone.services.RSAKeyPairGenerator;
import jala.university.chatcapstone.view.NavigationManager;
import jala.university.chatcapstone.view.PathViews;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatApplication extends Application {

  @Override
  public void start(Stage stage) throws IOException {
    RSAKeyPairGenerator.getInstance();
    NavigationManager navigationManager = NavigationManager.getInstance();
    navigationManager.initialize(stage);

    FXMLLoader fxmlLoader = new FXMLLoader(
        ChatApplication.class.getResource(PathViews.ChatApplicationView
        ));

    Scene scene = new Scene(fxmlLoader.load(), 600, 650);

    stage.setTitle("Chat Capstone");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}