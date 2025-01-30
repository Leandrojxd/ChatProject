package jala.university.ChatServerCapstone.presentation.controllers.authentication;

import jala.university.ChatServerCapstone.business.services.AuthService;
import jala.university.ChatServerCapstone.dataaccess.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private AuthService userService;
  private final Logger logger = LoggerFactory.getLogger(AuthController.class);
  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody User user) {
    try {
      userService.register(user);
      return ResponseEntity.ok("");
    } catch (RuntimeException exception) {
      return ResponseEntity.badRequest().body("Error: " + exception.getMessage());
    }
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody User user) {
    try {
      userService.login(user.getUsername(), user.getPassword());
      logger.info("User logged: " + user.getUsername());
      return ResponseEntity.ok("Login Success");
    } catch (Exception exception) {
      return ResponseEntity.badRequest().body(exception.getMessage());
    }
  }
}
