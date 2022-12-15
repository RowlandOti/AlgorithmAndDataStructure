package com.rowland.algorithms.sorting.domain

open class MergeSortState(open val id: Int) {
    data class DividedState(override val id: Int = 0) : MergeSortState(id = id)
    data class MergedState(override val id: Int = 1) : MergeSortState(id = id)
}
