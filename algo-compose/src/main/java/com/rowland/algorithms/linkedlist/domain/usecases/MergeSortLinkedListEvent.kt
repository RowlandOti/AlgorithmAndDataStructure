package com.rowland.algorithms.linkedlist.domain.usecases

import com.rowland.algorithms.linkedlist.Node
import com.rowland.algorithms.sorting.domain.MergeSortState
import java.util.*

data class MergeSortLinkedListEvent(
    val guid: String = UUID.randomUUID().toString(),
    val depth: Int,
    val sortParts: Node,
    val sortState: MergeSortState
)
