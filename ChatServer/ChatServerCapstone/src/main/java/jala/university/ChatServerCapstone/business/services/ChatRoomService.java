package jala.university.ChatServerCapstone.business.services;

import jala.university.ChatServerCapstone.dataaccess.models.ChatMessage;
import jala.university.ChatServerCapstone.dataaccess.models.ChatRoom;
import jala.university.ChatServerCapstone.dataaccess.models.EncryptedMessageUser;
import jala.university.ChatServerCapstone.dataaccess.models.PubKeyUser;
import jala.university.ChatServerCapstone.dataaccess.models.User;
import jala.university.ChatServerCapstone.dataaccess.repository.ChatRoomRepository;
import jala.university.ChatServerCapstone.dataaccess.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomService {

  @Autowired
  private ChatRoomRepository chatRoomRepository;

  @Autowired
  private UserRepository userRepository;

  public ChatRoom createRoom(String usernameOwner, String roomChatName) {
    if (checkUniqueRoomChatName(roomChatName)) {
      Optional<User> user = userRepository.findByUsername(usernameOwner);
      ChatRoom chatRoom = new ChatRoom(roomChatName, user.get().getId());
      chatRoomRepository.save(chatRoom);

      return chatRoom;
    } else {
      return null;
    }
  }

  public List<String> getChatRoomNamesByUsername(String username) {
    Optional<User> user = userRepository.findByUsername(username);
    List<ChatRoom> chatRooms = chatRoomRepository.findByMembersContaining(user.get().getId());
    return chatRooms.stream()
        .map(ChatRoom::getName)
        .collect(Collectors.toList());
  }

  private boolean checkUniqueRoomChatName(String roomChatName) {
    Optional<ChatRoom> chatRoom = chatRoomRepository.findByName(roomChatName);
    return chatRoom.isEmpty();
  }

  public ChatRoom getChatRoomByName(String chatRoomName) {
    Optional<ChatRoom> chatRoom = chatRoomRepository.findByName(chatRoomName);
    return chatRoom.get();
  }

  public ChatRoom joinChatRoom(String username, String chatRoomName) {
    Optional<User> newMemberChatRoom = userRepository.findByUsername(username);
    Optional<ChatRoom> chatRoom = chatRoomRepository.findByName(chatRoomName);
    chatRoom.get().getMembers().add(newMemberChatRoom.get().getId());
    chatRoomRepository.save(chatRoom.get());
    return chatRoom.get();
  }

  public void saveChatMessage(String username, String chatRoomName,
      List<EncryptedMessageUser> encryptedMessageUserList) {
    Optional<User> user = userRepository.findByUsername(username);
    Optional<ChatRoom> chatRoom = chatRoomRepository.findByName(chatRoomName);

    chatRoom.get().getChatMessage()
        .add(new ChatMessage(user.get().getUsername(), encryptedMessageUserList));
    chatRoomRepository.save(chatRoom.get());
  }

  public void sharePubKeyUser(String username, String chatRoomName, String publicKey) {
    Optional<ChatRoom> chatRoom = chatRoomRepository.findByName(chatRoomName);
    if (chatRoom.isPresent()) {
      List<PubKeyUser> pubKeyUserList = chatRoom.get().getPubKeyUserList();

      boolean exists = pubKeyUserList.stream()
          .anyMatch(
              user -> user.getUsername().equals(username) && user.getPublicKey().equals(publicKey));

      if (!exists) {
        pubKeyUserList.add(new PubKeyUser(username, publicKey));
        chatRoomRepository.save(chatRoom.get());
      }
    }
  }
}
