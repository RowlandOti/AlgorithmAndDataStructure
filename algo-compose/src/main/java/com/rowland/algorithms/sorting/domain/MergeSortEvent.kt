package com.rowland.algorithms.sorting.domain

import java.util.*

data class MergeSortEvent(
    val guid: String = UUID.randomUUID().toString(),
    val depth: Int,
    val sortParts: List<Int>,
    val sortState: MergeSortState
)
