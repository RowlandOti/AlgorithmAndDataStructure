package com.rowland.algorithms.sorting.presentation.state

import androidx.compose.ui.graphics.Color

data class SortUiItem(
    val id: Int,
    val value: Int,
    val color: Color,
    var comparingActive: Boolean = false,
    var swapped: Boolean = false,
)