package jala.university.ChatServerCapstone.dataaccess.repository;

import jala.university.ChatServerCapstone.dataaccess.models.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

  Optional<User> findByUsername(String username);
}
