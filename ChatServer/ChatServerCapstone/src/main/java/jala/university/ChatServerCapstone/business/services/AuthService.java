package jala.university.ChatServerCapstone.business.services;

import jala.university.ChatServerCapstone.dataaccess.models.User;
import jala.university.ChatServerCapstone.dataaccess.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  @Autowired
  private UserRepository userRepository;

  public User register(User user) {
    if (userRepository.findByUsername(user.getUsername()).isPresent()) {
      throw new RuntimeException("Username Already exists");
    }
    return userRepository.save(user);
  }

  public Optional<User> login(String username, String password) throws Exception {
    Optional<User> user = userRepository.findByUsername(username);
    if (user.isPresent() && user.get().getPassword().equals(password)) {
      return user;
    }

    verifyIfUserExists(user);
    verifyPassword(user.get().getPassword(), password);

    return Optional.empty();
  }

  private void verifyIfUserExists(Optional<User> user) throws Exception {
    if (user.isEmpty()) {
      throw new Exception("User don't exists");
    }
  }

  private void verifyPassword(String originalPassword, String inputPassword) throws Exception {
    if (!originalPassword.equals(inputPassword)) {
      throw new Exception("Incorrect Credentials");
    }
  }
}
