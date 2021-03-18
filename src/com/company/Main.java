package com.company;

public class Main {
    public static void main(String[] args) {
        String apiKey = System.getenv("GEM_API_KEY");
        String secret = System.getenv("GEM_API_SECRET");
        String baseURL = System.getenv("GEM_API_URL");
        Gem gem = new Gem(baseURL, apiKey, secret);
        gem.getTransaction("1234");
    }
}
