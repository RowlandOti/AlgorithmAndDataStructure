package com.rowland.algorithms.sorting

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data class SortEvent(
    val currentItemIndex: Int,
    var shouldSwap: Boolean = false,
    var skipped: Boolean = false
)

fun swap(array: MutableList<Int>, backIndex: Int, forwardIndex: Int) {
    val temp = array[forwardIndex]
    array[forwardIndex] = array[backIndex]
    array[backIndex] = temp
}

interface SortUseCase {
    fun sort(data: MutableList<Int>): Flow<SortEvent>
}

class BubbleSortUseCase : SortUseCase {
    override fun sort(data: MutableList<Int>): Flow<SortEvent> = flow {
        for (outerIndex in 0 until data.size - 1) {
            for (innerIndex in 0 until data.size - 1 - outerIndex) {

                emit(SortEvent(currentItemIndex = innerIndex, shouldSwap = false, skipped = false))
                delay(500)

                if (data[innerIndex]> data[innerIndex + 1]) {
                    swap(data, innerIndex, innerIndex + 1)
                    emit(SortEvent(currentItemIndex = innerIndex, shouldSwap = true, skipped = false))
                } else {
                    emit(SortEvent(currentItemIndex = innerIndex, shouldSwap = false, skipped = true))
                }

                delay(500)
            }
        }
    }
}