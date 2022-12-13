package com.rowland.algorithms.sorting

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class SortUiItem(
    val id: Int,
    val value: Int,
    val color: Color,
    var comparingActive: Boolean = false,
    var swapped: Boolean = false,
)

class SortingViewModel(
    private val sortUseCase: SortUseCase,
    val blocks: MutableList<SortUiItem>
) : ViewModel() {

    fun sort() {
        viewModelScope.launch {
            sortUseCase.sort(blocks.map { sortUiItem -> sortUiItem.value }.toMutableList())
                .collect { sortEvent ->
                    val firstIndex = sortEvent.firstItemIndex
                    val secondIndex = sortEvent.secondItemIndex

                    blocks[firstIndex] = blocks[firstIndex].copy(comparingActive = true)
                    blocks[secondIndex] = blocks[secondIndex].copy(comparingActive = true)

                    if (sortEvent.shouldSwap) {
                        val temp = blocks[firstIndex].copy(comparingActive = false, swapped = true)
                        blocks[firstIndex] =
                            blocks[secondIndex].copy(comparingActive = false, swapped = false)
                        blocks[secondIndex] = temp
                    }

                    delay(500)

                    blocks[firstIndex] =
                        blocks[firstIndex].copy(comparingActive = false, swapped = false)
                    blocks[secondIndex] =
                        blocks[secondIndex].copy(comparingActive = false, swapped = false)

                    delay(500)

                    if (sortEvent.skipped) {
                        blocks[firstIndex] = blocks[firstIndex].copy(comparingActive = false, swapped = false)
                        blocks[secondIndex] =
                            blocks[secondIndex].copy(comparingActive = false, swapped = false)
                    }
                }
        }
    }
}