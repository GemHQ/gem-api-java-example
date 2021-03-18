package com.company;

import java.net.URI;
import java.time.Instant;
import java.math.BigInteger;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class Gem {
  // Default to production
  static String baseURL = "https://api.gem.co";
  static String APIKey;
  static String APISecret;

  public Gem(String url, String apiKey, String secret) {
    baseURL = url;
    APIKey = apiKey;
    APISecret = secret;
  }

  // Get a single transaction by ID
  public Void getTransaction(String txnId) {
    String reqURL = String.format("%s/transactions/%s", baseURL, txnId);
    String timeStamp = getTimeStamp();
    String signature = getSignature(timeStamp);
    HttpClient client = HttpClient.newHttpClient();

    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create(reqURL))
      .header("X-Gem-Access-Timestamp", timeStamp)
      .header("X-Gem-Api-Key", APIKey)
      .header("X-Gem-Signature", signature)
      .build();

    return client.sendAsync(request, BodyHandlers.ofString())
      .thenApply(HttpResponse::body)
      .thenAccept(System.out::println)
      .join();
  }

  // Generate an API signature
  private static String getSignature(String timeStamp) {
    String data = String.format("%s:%s", APIKey, timeStamp);
    byte[] signature = Hmac.sha256(APISecret, data);
    BigInteger hexSignature = new BigInteger(1, signature);
    return String.format("%032x", hexSignature);
  }

  // Get unix timestamp (seconds) as a string
  private static String getTimeStamp() {
    return String.valueOf(Instant.now().getEpochSecond());
  }
}
