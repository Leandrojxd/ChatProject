module com.codeflu.chatclient {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.kordamp.bootstrapfx.core;
  requires java.net.http;
  requires com.google.gson;
  requires com.fasterxml.jackson.databind;
  requires org.eclipse.paho.client.mqttv3;

  opens jala.university.chatcapstone to javafx.fxml;
  opens jala.university.chatcapstone.view to javafx.fxml;

  exports jala.university.chatcapstone;
  exports jala.university.chatcapstone.view;

  opens jala.university.chatcapstone.model to com.fasterxml.jackson.databind;
}
