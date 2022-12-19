package com.rowland.algorithms.linkedlist.presentation.state

import androidx.compose.ui.graphics.Color
import com.rowland.algorithms.linkedlist.Node
import com.rowland.algorithms.sorting.domain.MergeSortState
import java.util.*

data class MergeSortLinkedListUiItem(
    val guid: String = UUID.randomUUID().toString(),
    val depth: Int,
    val sortParts: List<Node>,
    val sortState: MergeSortState,
    val color: Color
)
