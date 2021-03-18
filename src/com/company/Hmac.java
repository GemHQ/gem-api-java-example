package com.company;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class Hmac {
  static public byte[] sha256(String secretKey, String message) {
    byte[] sha256 = null;
    try {
        Mac mac = Mac.getInstance("HmacSHA256");
        byte[] secret = secretKey.getBytes(StandardCharsets.UTF_8);
        byte[] data = message.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secret, "HmacSHA256");
        mac.init(secretKeySpec);
        sha256 = mac.doFinal(data);
    } catch (Exception e) {
        throw new RuntimeException("Failed to create HMAC SHA256", e);
    }
    return sha256;
  }
}
