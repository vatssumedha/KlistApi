# KListApi – Fluent DSL for Jetpack Compose Lists

KListApi is a modular, reusable list rendering API written in Kotlin using Jetpack Compose. It allows you to build powerful list screens using a clean, fluent DSL.

---

## Features

- Chainable configuration using Kotlin DSL
- Supports padding, headers, sections, and click events
- Works with any data type
- Optional dividers and animations
- Material3 styling support
- Designed for readability, reusability, and extendability

---

## Usage Example

```kotlin
@Composable
fun KListDemo() {
    val context = LocalContext.current
    val topCoins = CoinRepository.getTopCoins()
    val memeCoins = CoinRepository.getMemeCoins()

    KList.instance
        .padding(16.dp)
        .header("Top Gainers")
        .items(topCoins) { KListItem(it) }
        .clickable { item ->
            Toast.makeText(context, item.toString(), Toast.LENGTH_SHORT).show()
        }
        .section("Meme Coins", memeCoins) { KListItem(it) }
        .Render()
}
