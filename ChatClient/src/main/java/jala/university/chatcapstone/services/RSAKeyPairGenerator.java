package jala.university.chatcapstone.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

public class RSAKeyPairGenerator {

  private static final String PRIVATE_KEY_FILE = "privateKey.key";
  private static final String PUBLIC_KEY_FILE = "publicKey.key";

  private static RSAKeyPairGenerator instance;
  private PrivateKey privateKey;
  private PublicKey publicKey;

  private RSAKeyPairGenerator() {
    try {
      if (!loadKeysFromFile()) {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        KeyPair pair = keyPairGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
        saveKeysToFile();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static synchronized RSAKeyPairGenerator getInstance() {
    if (instance == null) {
      instance = new RSAKeyPairGenerator();
    }
    return instance;
  }

  private boolean loadKeysFromFile() {
    try {
      File privateKeyFile = new File(PRIVATE_KEY_FILE);
      File publicKeyFile = new File(PUBLIC_KEY_FILE);
      if (privateKeyFile.exists() && publicKeyFile.exists()) {
        try (ObjectInputStream privateKeyIS = new ObjectInputStream(new FileInputStream(privateKeyFile));
            ObjectInputStream publicKeyIS = new ObjectInputStream(new FileInputStream(publicKeyFile))) {
          this.privateKey = (PrivateKey) privateKeyIS.readObject();
          this.publicKey = (PublicKey) publicKeyIS.readObject();
          return true;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  private void saveKeysToFile() {
    try (ObjectOutputStream privateKeyOS = new ObjectOutputStream(new FileOutputStream(PRIVATE_KEY_FILE));
        ObjectOutputStream publicKeyOS = new ObjectOutputStream(new FileOutputStream(PUBLIC_KEY_FILE))) {
      privateKeyOS.writeObject(this.privateKey);
      publicKeyOS.writeObject(this.publicKey);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String getPrivateKeyBase64() {
    RSAKeyPairGenerator instance = getInstance();
    return Base64.getEncoder().encodeToString(instance.privateKey.getEncoded());
  }

  public String getPublicKeyBase64() {
    RSAKeyPairGenerator instance = getInstance();
    return Base64.getEncoder().encodeToString(instance.publicKey.getEncoded());
  }

  public String encrypt(String data, String publicKeyBase64) {
    try {
      byte[] keyBytes = Base64.getDecoder().decode(publicKeyBase64);
      X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      PublicKey publicKey = keyFactory.generatePublic(spec);

      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(Cipher.ENCRYPT_MODE, publicKey);
      byte[] encryptedData = cipher.doFinal(data.getBytes());
      return Base64.getEncoder().encodeToString(encryptedData);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public String decrypt(String encryptedData) {
    try {
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(Cipher.DECRYPT_MODE, privateKey);
      byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
      return new String(decryptedData);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
