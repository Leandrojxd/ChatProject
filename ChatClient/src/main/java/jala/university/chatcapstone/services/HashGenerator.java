package jala.university.chatcapstone.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class HashGenerator {

  public String generateSHA256Hash(String message) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] sha256HashBytes = digest.digest(message.getBytes(StandardCharsets.UTF_8));
      StringBuilder hexString = new StringBuilder();
      for (byte b : sha256HashBytes) {
        hexString.append(String.format("%02x", b));
      }
      return hexString.toString();
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("Error al generar el hash SHA-256: " + e.getMessage());
    }
  }
}
