package com.company;

public class Main {
    public static void main(String[] args) {
        String apiKey = System.getenv("GEM_API_KEY");
        String secret = System.getenv("GEM_API_SECRET");
        Gem gem = new Gem("https://api.sandbox.gem.co", apiKey, secret);
        gem.getTransaction("1234");
    }
}
