# KListApi – Fluent DSL for Jetpack Compose Lists

KListApi is a Kotlin-based modular and reusable API for building list-based UIs using Jetpack Compose. It introduces a fluent DSL-style syntax that allows developers to define padding, headers, sections, clickable events, and animations for list content with ease and clarity.

---

## Features

- Fluent Kotlin DSL using method chaining
- Composable integration using `LazyColumn`
- Configurable padding and header
- Support for clickable items
- Support for multiple sections with headers
- Optional item dividers and animations
- Modular file structure for maintainability
- Material3 styling support

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
        .items(topCoins) {
            KListItem(it)
        }
        .clickable { item ->
            Toast.makeText(context, item.toString(), Toast.LENGTH_SHORT).show()
        }
        .section("Meme Coins", memeCoins) {
            KListItem(it)
        }
        .Render()
}
```

---

## Project Structure

| File               | Description                                 |
|--------------------|---------------------------------------------|
| `KList.kt`         | Core DSL class with chainable modifiers     |
| `KListRenderer.kt` | Contains rendering logic with LazyColumn    |
| `KListSection.kt`  | Section data model for list configuration   |
| `KListItem.kt`     | Composable for rendering a single item      |
| `KListDemo.kt`     | Composable function demonstrating usage     |
| `CoinRepository.kt`| Provides fake coin data for demo            |
| `MainActivity.kt`  | Launches the Compose-based home screen      |
| `theme/`           | Custom Material 3 color and typography setup|

---

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/vatssumedha/KlistApi.git
   ```
2. Open in **Android Studio** (preferably Arctic Fox or later)
3. Run on an emulator or device with Compose support

---

## Known Issues

- Type casting via `Any` introduces unchecked cast warnings
- Sticky headers not implemented
- Paging/loading not included
- Only one type of item per list section

---

## Future Enhancements

- Add sticky headers using `LazyListScope.stickyHeader` to improve section visibility while scrolling
- Integrate paging support via Jetpack's Paging 3 library to handle large data sets efficiently
- Implement loading indicators (e.g., shimmer effect) and empty state UIs to improve UX
- Use sealed classes or type-safe generics to support multiple item types within a single list
- Add preview support using `@Preview` annotations for reusable components
