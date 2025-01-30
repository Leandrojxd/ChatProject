package jala.university.chatcapstone.services;

import static jala.university.chatcapstone.controller.consts.UrlServices.AUTH;
import static jala.university.chatcapstone.controller.consts.UrlServices.LOCALHOST;
import static jala.university.chatcapstone.controller.consts.UrlServices.SUFFIX_LOGIN;
import static jala.university.chatcapstone.controller.consts.UrlServices.SUFFIX_REGISTER;

import com.fasterxml.jackson.databind.ObjectMapper;
import jala.university.chatcapstone.controller.RequestHandler;
import jala.university.chatcapstone.model.User;

public class AuthServices {

  private final StringBuilder stringBuilder;
  private static AuthServices instance;
  private final RequestHandler requestHandler;
  private final ObjectMapper objectMapper;

  private AuthServices() {
    this.stringBuilder = new StringBuilder();
    this.requestHandler = new RequestHandler();
    this.objectMapper = new ObjectMapper();
  }

  public static AuthServices getInstance() {
    if (instance == null) {
      instance = new AuthServices();
    }
    return instance;
  }

  public String register(String username, String password) {
    stringBuilder.setLength(0);
    String url = stringBuilder.append(LOCALHOST).append(AUTH).append(SUFFIX_REGISTER).toString();
    User user = new User(username, password);
    String jsonStringUser = convertUserToJson(user);
    return requestHandler.post(url, jsonStringUser);
  }

  public String login(String username, String password) {
    stringBuilder.setLength(0);
    String url = stringBuilder.append(LOCALHOST).append(AUTH).append(SUFFIX_LOGIN).toString();
    User user = new User(username, password);
    String jsonStringUser = convertUserToJson(user);
    return requestHandler.post(url, jsonStringUser);
  }

  private String convertUserToJson(User user) {
    String jsonUser;
    try {
      jsonUser = objectMapper.writeValueAsString(user);
    } catch (Exception exception) {
      jsonUser = null;
    }
    return jsonUser;
  }
}
