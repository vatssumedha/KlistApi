package com.example.klistapi


// Repository object to provide fake/dummy coin data

object CoinRepository {

    // Returns a list of 25 top coins
    // Each item is a simple string like "Top Coin #1", "Top Coin #2", etc.
    fun getTopCoins(): List<String> {
        return List(25) { index -> "Top Coin #${index + 1}" }
    }

    // Returns a list of 25 meme coins
    // Simulates data like "Meme Coin #1", "Meme Coin #2", etc.
    fun getMemeCoins(): List<String> {
        return List(25) { index -> "Meme Coin #${index + 1}" }
    }
}