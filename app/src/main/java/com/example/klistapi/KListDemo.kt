package com.example.klistapi

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

/**
 * Demonstrates how to use the KList DSL to render a custom list screen.
 * This function defines two lists: top coins and meme coins,
 * and shows how to attach headers, padding, and item click handling.
 */
@Composable
fun KListDemo() {
    // Access the current Context to show Toast
    val context = LocalContext.current

    // Get a list of 25 top coins from the repository
    val topCoins = CoinRepository.getTopCoins()

    // Get a list of 25 meme coins from the repository
    val memeCoins = CoinRepository.getMemeCoins()

    // Configure and render the list using fluent DSL methods
    KList.instance
        .padding(16.dp) // Set outer padding for the entire list
        .header(stringResource(R.string.header_top_gainers)) // Add a top header using a localized string
        .items(topCoins) { coin -> KListItem(coin) } // Add the first list of items without a section title
        .clickable { item ->
            // Convert item to string and show as Toast
            Toast.makeText(context, item.toString(), Toast.LENGTH_SHORT).show()
        }
        .section(
            stringResource(R.string.header_meme_coins),
            memeCoins
        ) { coin -> KListItem(coin) } // Add a second section with a title
        .Render() // Render the configured list
}
