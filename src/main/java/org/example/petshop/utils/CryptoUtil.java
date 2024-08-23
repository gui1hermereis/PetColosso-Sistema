package org.example.petshop.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CryptoUtil {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";

    public static SecretKey getKeyFromString(String keyString) {
        byte[] keyBytes = Base64.getDecoder().decode(keyString);
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }

    public static SecretKey getTestKey() {
        String keyString = "q3aThT8QgGeMkEUNaLOO5X2BLzXpBQiLxjc+IXld97E=";
        return getKeyFromString(keyString);
    }

    public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }
}