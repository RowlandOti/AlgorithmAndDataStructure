package com.rowland.algorithms.sorting.presentation.state

import androidx.compose.ui.graphics.Color
import com.rowland.algorithms.sorting.domain.MergeSortState
import java.util.*

data class MergeSortUiItem(
    val guid: String = UUID.randomUUID().toString(),
    val depth: Int,
    val sortParts: List<List<Int>>,
    val sortState: MergeSortState,
    val color: Color
)
