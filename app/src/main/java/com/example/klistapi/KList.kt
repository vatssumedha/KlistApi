package com.example.klistapi

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * KList is a fluent-style DSL class that helps configure and render
 * a composable list screen using Jetpack Composes LazyColumn.
 */

class KList private constructor() {

    // Padding for the entire list layout
    private var padding: Dp = 0.dp

    // Optional top-level header text
    private var topHeader: String? = null

    // Optional click listener for list items
    private var onItemClick: ((Any) -> Unit)? = null

    // Holds all sections or unnamed lists configured by the user
    private val sections: MutableList<KListSection<*>> = mutableListOf()

    companion object {
        // Entry point to create and configure a KList instance using fluent syntax
        val instance: KList
            get() = KList()
    }

    /**
     * Set padding for the full list container.
     * @param value The padding in Dp
     */
    fun padding(value: Dp): KList = apply {
        this.padding = value
    }

    /**
     * Add a main header title displayed at the top of the list.
     * @param title The header text
     */
    fun header(title: String): KList = apply {
        this.topHeader = title
    }

    /**
     * Attach a click handler for each list item.
     * @param onClick Lambda invoked with the clicked item
     */
    fun clickable(onClick: (Any) -> Unit): KList = apply {
        this.onItemClick = onClick
    }

    /**
     * Define a single section less list of items to be displayed.
     * @param data List of items to render
     * @param itemContent Composable lambda that defines how each item is rendered
     */
    fun <T> items(data: List<T>, itemContent: @Composable (T) -> Unit): KList = apply {
        sections.add(KListSection(null, data, itemContent))
    }

    /**
     * Define a titled section with its own list of items.
     * @param title Title of the section
     * @param data List of items under this section
     * @param itemContent Composable lambda that defines how each item is rendered
     */
    fun <T> section(title: String, data: List<T>, itemContent: @Composable (T) -> Unit): KList = apply {
        sections.add(KListSection(title, data, itemContent))
    }

    /**
     * Render the final KList screen using Compose.
     * This must be called from within a @Composable function.
     */
    @Composable
    fun Render() {
        KListRenderer(
            padding = padding,
            header = topHeader,
            sections = sections,
            onItemClick = onItemClick
        )
    }
}


