package jala.university.ChatServerCapstone.dataaccess.repository;

import jala.university.ChatServerCapstone.dataaccess.models.ChatRoom;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {

  Optional<ChatRoom> findByName(String name);

  List<ChatRoom> findByMembersContaining(String userId);
}
