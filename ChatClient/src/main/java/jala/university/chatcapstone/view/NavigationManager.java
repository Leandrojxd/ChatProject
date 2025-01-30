package jala.university.chatcapstone.view;

import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NavigationManager {

  private static NavigationManager instance;
  private Stage primaryStage;

  private NavigationManager() {
  }

  public Stage getPrimaryStage() {
    return primaryStage;
  }

  public static NavigationManager getInstance() {
    if (instance == null) {
      instance = new NavigationManager();
    }
    return instance;
  }

  public void initialize(Stage stage) {
    this.primaryStage = stage;
  }

  public <T> void navigateTo(String fxmlFile, ControllerConsumer<T> consumer) {
    try {
      FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(fxmlFile)));
      Parent root = loader.load();
      T controller = loader.getController();
      consumer.accept(controller);
      Scene scene = new Scene(root);
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }

  public void navigateTo(String fxmlFile) {
    try {
      Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
      Scene scene = new Scene(root);
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }

  @FunctionalInterface
  public interface ControllerConsumer<T> {

    void accept(T controller);
  }
}
