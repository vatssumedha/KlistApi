package com.example.klistapi

import androidx.compose.runtime.Composable


/**
 * Holds a group of list items, optionally with a section title.
 */
data class KListSection<T>(
    val title: String?,                         // Optional section title
    val items: List<T>,                         // List of data items
    val itemContent: @Composable (T) -> Unit    // Composable to render each item
)
