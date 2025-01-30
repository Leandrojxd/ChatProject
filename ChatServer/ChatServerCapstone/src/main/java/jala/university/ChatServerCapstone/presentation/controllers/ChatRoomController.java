package jala.university.ChatServerCapstone.presentation.controllers;

import jala.university.ChatServerCapstone.business.services.ChatRoomService;
import jala.university.ChatServerCapstone.dataaccess.models.ChatRoom;
import jala.university.ChatServerCapstone.dataaccess.models.EncryptedMessageUser;
import jala.university.ChatServerCapstone.presentation.controllers.dto.CreateRoomDto;
import jala.university.ChatServerCapstone.presentation.controllers.dto.JoinChatRoomDTO;
import jala.university.ChatServerCapstone.presentation.controllers.dto.PubKeyUserDTO;
import jala.university.ChatServerCapstone.presentation.controllers.dto.RegisterMessageDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chatRoom")
public class ChatRoomController {

  @Autowired
  private ChatRoomService chatRoomService;

  @PostMapping
  public ResponseEntity<ChatRoom> create(@RequestBody CreateRoomDto createRoomDto) {
    String usernameOwner = createRoomDto.getUsernameOwner();
    String nameChatRoom = createRoomDto.getNameChatRoom();
    ChatRoom createdChatRoom = chatRoomService.createRoom(usernameOwner, nameChatRoom);
    return ResponseEntity.ok(createdChatRoom);
  }

  @GetMapping("/byUser")
  public ResponseEntity<List<String>> getChatRoomNamesByUserId(@RequestParam String username) {
    return ResponseEntity.ok(chatRoomService.getChatRoomNamesByUsername(username));
  }

  @GetMapping
  public ResponseEntity<ChatRoom> getChatRoomByName(@RequestParam String chatRoomName) {
    return ResponseEntity.ok(chatRoomService.getChatRoomByName(chatRoomName));
  }

  @PutMapping
  public ResponseEntity<ChatRoom> joinChatRoom(@RequestBody JoinChatRoomDTO joinChatRoomDTO) {
    String username = joinChatRoomDTO.getUsername();
    String nameChatRoom = joinChatRoomDTO.getChatRoomName();
    return ResponseEntity.ok(chatRoomService.joinChatRoom(username, nameChatRoom));
  }

  @PutMapping("/addMessage")
  public ResponseEntity<String> addMessage(@RequestBody RegisterMessageDTO registerMessageDTO) {
    String username = registerMessageDTO.getUsername();
    String chatRoomName = registerMessageDTO.getChatRoomName();
    List<EncryptedMessageUser> encryptedMessageUserList = registerMessageDTO.getEncryptedMessageUserList();
    chatRoomService.saveChatMessage(username, chatRoomName, encryptedMessageUserList);
    return ResponseEntity.ok("Saved message");
  }

  @PostMapping("/sharePublicKey")
  public ResponseEntity<String> sharePubKey(@RequestBody PubKeyUserDTO registerPubKeyUserDTO) {
    String username = registerPubKeyUserDTO.getUsername();
    String chatRoomName = registerPubKeyUserDTO.getChatRoomName();
    String publicKey = registerPubKeyUserDTO.getPublicKey();
    chatRoomService.sharePubKeyUser(username, chatRoomName, publicKey);
    return ResponseEntity.ok("shared PubKey");
  }
}
