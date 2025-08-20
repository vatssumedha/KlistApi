package com.example.klistapi

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * KList is a fluent-style DSL class that helps configure and render
 * a composable list screen using Jetpack Composes LazyColumn.
 */

class KList private constructor() {

    private var padding: Dp = 0.dp


    private var topHeader: String? = null


    private var onItemClick: ((Any) -> Unit)? = null


    private val sections: MutableList<KListSection<*>> = mutableListOf()

    companion object {
        val instance: KList
            get() = KList()
    }


    fun padding(value: Dp): KList = apply {
        this.padding = value
    }

    fun header(title: String): KList = apply {
        this.topHeader = title
    }


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


