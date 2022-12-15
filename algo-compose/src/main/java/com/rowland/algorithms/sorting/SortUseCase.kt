package com.rowland.algorithms.sorting

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import java.util.*

data class SortEvent(
    val firstItemIndex: Int,
    val secondItemIndex: Int,
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

                emit(
                    SortEvent(
                        firstItemIndex = innerIndex,
                        secondItemIndex = innerIndex+1,
                        shouldSwap = false,
                        skipped = false
                    )
                )
                delay(500)

                if (data[innerIndex] > data[innerIndex + 1]) {
                    swap(data, innerIndex, innerIndex + 1)
                    emit(
                        SortEvent(
                            firstItemIndex = innerIndex,
                            secondItemIndex = innerIndex+1,
                            shouldSwap = true,
                            skipped = false
                        )
                    )
                } else {
                    emit(
                        SortEvent(
                            firstItemIndex = innerIndex,
                            secondItemIndex = innerIndex+1,
                            shouldSwap = false,
                            skipped = true
                        )
                    )
                }

                delay(500)
            }
        }
    }
}

class SelectionSortUseCase : SortUseCase {
    override fun sort(data: MutableList<Int>): Flow<SortEvent> = flow {
        for (outerIndex in 0 until data.size) {
            var currMinimum = data[outerIndex]
            var currMinimumIndex = outerIndex

            for (innerIndex in outerIndex until data.size) {
                emit(
                    SortEvent(
                        firstItemIndex = outerIndex,
                        secondItemIndex = innerIndex,
                        shouldSwap = false,
                        skipped = false
                    )
                )
                delay(250)

                if (data[innerIndex] < currMinimum) {
                    currMinimum = data[innerIndex]
                    currMinimumIndex = innerIndex
                }
            }

            if(currMinimumIndex != outerIndex) {
                swap(data, outerIndex, currMinimumIndex)
                emit(
                    SortEvent(
                        firstItemIndex = outerIndex,
                        secondItemIndex = currMinimumIndex,
                        shouldSwap = true,
                        skipped = false
                    )
                )
                delay(500)
            }
        }

    }
}

class InsertionSortUseCase : SortUseCase {
    override fun sort(data: MutableList<Int>): Flow<SortEvent> = flow {
        for (outerIndex in 0 until data.size) {
            val temp: Int = data[outerIndex]
            var innerIndex = outerIndex
            while (innerIndex > 0 && temp < data[innerIndex - 1]) {
                data[innerIndex] = data[innerIndex - 1]
                emit(
                    SortEvent(
                        firstItemIndex = innerIndex,
                        secondItemIndex = innerIndex-1,
                        shouldSwap = true,
                        skipped = false
                    )
                )
                innerIndex -= 1
            }
            data[innerIndex] = temp
        }
    }
}

class QuickSortUseCase : SortUseCase {
    override fun sort(data: MutableList<Int>): Flow<SortEvent> = flow {
        sortingHelperRecursive(
            data = data,
            start = 0,
            end = data.size - 1,
            flows = mutableListOf()
        ).forEach {
            emit(it)
            delay(500)
        }
    }

    private fun sortingHelperRecursive(
        data: MutableList<Int>,
        start: Int,
        end: Int,
        flows: MutableList<SortEvent>
    ): List<SortEvent> {
        if (start < end) {
            val pivotIndex = partition(data, start, end, flows)
            sortingHelperRecursive(data = data, start = start, end = pivotIndex - 1, flows)
            sortingHelperRecursive(data = data, start = pivotIndex + 1, end = end, flows)
        }

        return flows
    }

    private fun sortingHelperIterative(
        data: MutableList<Int>,
        start: Int = 0,
        end: Int
    ): Flow<SortEvent> = flow {
        val stack = Stack<Pair<Int, Int>>()
        stack.push(Pair(start, end))

        while (!stack.empty()) {
            val curr = stack.pop()

            val pivot = data[curr.second]
            var pivotIndex = curr.first
            for (i in curr.first until curr.second) {
                emit(
                    SortEvent(
                        firstItemIndex = i,
                        secondItemIndex = curr.second,
                        shouldSwap = false,
                        skipped = false
                    )
                )
                delay(500)

                if (data[i] <= pivot) {
                    swap(array = data, pivotIndex, i)
                    emit(
                        SortEvent(
                            firstItemIndex = i,
                            secondItemIndex = curr.second,
                            shouldSwap = true,
                            skipped = false
                        )
                    )
                    pivotIndex += 1
                } else {
                    emit(
                        SortEvent(
                            firstItemIndex = i,
                            secondItemIndex = curr.second,
                            shouldSwap = false,
                            skipped = true
                        )
                    )
                }

                delay(500)
            }
            swap(array = data, pivotIndex, curr.second)
            emit(
                SortEvent(
                    firstItemIndex = pivotIndex,
                    secondItemIndex = curr.second,
                    shouldSwap = true,
                    skipped = false
                )
            )

            delay(500)

            if (pivotIndex - 1 > curr.first) {
                stack.push(Pair(curr.first, pivotIndex - 1))
            }

            if (pivotIndex + 1 < curr.second) {
                stack.push(Pair(pivotIndex + 1, curr.second))
            }
        }
    }

    private fun partition(
        data: MutableList<Int>,
        start: Int,
        end: Int,
        flows: MutableList<SortEvent>
    ): Int {
        val pivot = data[end]

        var index = start

        for (i in start until end) {
            flows.add(
                SortEvent(
                    firstItemIndex = i,
                    secondItemIndex = index,
                    shouldSwap = false,
                    skipped = false
                )
            )
            if (data[i] <= pivot) {
                swap(array = data, index, i)
                flows.add(
                    SortEvent(
                        firstItemIndex = i,
                        secondItemIndex = index,
                        shouldSwap = true,
                        skipped = false
                    )
                )
                index += 1
            } else {
                flows.add(
                    SortEvent(
                        firstItemIndex = i,
                        secondItemIndex = index,
                        shouldSwap = false,
                        skipped = true
                    )
                )
            }
        }

        swap(array = data, index, end)
        flows.add(
            SortEvent(
                firstItemIndex = end,
                secondItemIndex = index,
                shouldSwap = true,
                skipped = false
            )
        )
        return index
    }
}