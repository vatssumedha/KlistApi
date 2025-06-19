package com.example.klistapi

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * KListRenderer is the internal Composable used by KList to render the configured UI.
 * It uses a LazyColumn to efficiently display headers, sections, and animated list items.
 */
@Composable
fun KListRenderer(
    padding: Dp,                           // Outer padding for the entire list
    header: String?,                      // Optional top-level header
    sections: List<KListSection<*>>,     // One or more sections of items to display
    onItemClick: ((Any) -> Unit)?        // Optional click listener for each item
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding) // Apply user-defined padding
    ) {
        // Render the main/top-level header if provided
        header?.let {
            item {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp, start = 8.dp, end = 8.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        // Iterate through each section to render its title and items
        sections.forEach { rawSection ->
            // If the section has a title, render it
            val title = rawSection.title
            if (title != null) {
                item {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp, start = 8.dp, end = 8.dp, bottom = 15.dp))
                }
            }

            // Safe casting to KListSection<Any> to handle generic types
            @Suppress("UNCHECKED_CAST")
            val section = rawSection as KListSection<Any>

            // Render each item in the section using itemsIndexed for access to index
            itemsIndexed(section.items) { index, item ->
                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn() + expandVertically(), // Fade + expand animation
                    exit = fadeOut()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(enabled = onItemClick != null) {
                                onItemClick?.invoke(item) // Trigger click callback if set
                            }
                    ) {
                        section.itemContent(item) // Render the actual item using provided Composable

                        // Add a divider between items, except after the last one
                        if (index < section.items.size - 1) {
                            HorizontalDivider(Modifier.padding(horizontal = 12.dp))
                        }
                    }
                }
            }
        }
    }
}
