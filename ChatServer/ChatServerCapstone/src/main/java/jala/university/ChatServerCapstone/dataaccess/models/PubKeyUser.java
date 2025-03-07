package jala.university.ChatServerCapstone.dataaccess.models;

public class PubKeyUser {

  private String username;
  private String publicKey;

  public PubKeyUser(String username, String publicKey) {
    this.username = username;
    this.publicKey = publicKey;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }
}
