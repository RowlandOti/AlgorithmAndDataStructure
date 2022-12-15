package com.rowland.algorithms.sorting.domain.usecases

import com.rowland.algorithms.sorting.domain.MergeSortEvent
import com.rowland.algorithms.sorting.domain.MergeSortState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import java.util.UUID

class MergeSortUseCase {

    val sortSharedFlow = MutableSharedFlow<MergeSortEvent>()

    suspend fun sort(data: MutableList<Int>, depth: Int = 0) {
        divide(data = data, depth)
    }

    private suspend fun divide(data: MutableList<Int>, depth: Int): MutableList<Int> {
        delay(500)
        sortSharedFlow.emit(
            MergeSortEvent(
                depth = depth,
                sortParts = data,
                sortState = MergeSortState.DividedState()
            )
        )

        if (data.size <= 1) {
            return data
        }

        val middle = (data.size) / 2
        var left = data.subList(0, middle)
        var right = data.subList(middle, data.size)

        left = divide(data = left, depth = depth + 1)
        right = divide(data = right, depth = depth + 1)

        return merge(left, right, depth)
    }

    private suspend fun merge(
        left: MutableList<Int>,
        right: MutableList<Int>,
        depth: Int
    ): MutableList<Int> {
        val result = mutableListOf<Int>()
        var leftIndex = 0
        var rightIndex = 0

        while (leftIndex < left.size && rightIndex < right.size) {
            if (left[leftIndex] <= right[rightIndex]) {
                result.add(left[leftIndex])
                leftIndex += 1
            } else {
                result.add(right[rightIndex])
                rightIndex += 1
            }
        }

        while (leftIndex < left.size) {
            result.add(left[leftIndex])
            leftIndex += 1
        }

        while (rightIndex < right.size) {
            result.add(right[rightIndex])
            rightIndex += 1
        }

        delay(500)
        sortSharedFlow.emit(
            MergeSortEvent(
                depth = depth,
                sortParts = result,
                sortState = MergeSortState.MergedState()
            )
        )

        return result
    }
}


fun mergeSort(data: MutableList<Int>, depth: Int = 0): MutableList<Int> {
    if(data.size <=1){
        return data
    }

    val middle = (data.size)/ 2

    var left = data.subList(0, middle)
    var right = data.subList(middle, data.size)

    println("Depth: $depth --> Divided $left: $right")

    left = mergeSort(left, depth+1)
    right = mergeSort(right, depth+1)

    return merge(left, right, depth)
}

fun merge(left: MutableList<Int>, right: MutableList<Int>, depth: Int): MutableList<Int> {
    val result = mutableListOf<Int>()
    var leftIndex = 0
    var rightIndex = 0

    while(leftIndex < left.size && rightIndex < right.size){
        if(left[leftIndex] < right[rightIndex]){
            result.add(left[leftIndex])
            leftIndex++
        }else{
            result.add(right[rightIndex])
            rightIndex++
        }
    }

    while(leftIndex < left.size){
        result.add(left[leftIndex])
        leftIndex++
    }

    while(rightIndex < right.size){
        result.add(right[rightIndex])
        rightIndex++
    }
    println("Depth: $depth --> Merged: $result")
    return result
}
