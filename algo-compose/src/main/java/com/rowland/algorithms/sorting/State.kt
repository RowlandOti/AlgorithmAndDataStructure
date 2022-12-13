package com.rowland.algorithms.sorting

import androidx.compose.runtime.mutableStateListOf
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

class SortingViewModel(private val sortUseCase: SortUseCase) : ViewModel() {

    val blocks = mutableStateListOf<SortUiItem>()

    init {
        createRandomBlocks()
    }

    fun createRandomBlocks(): MutableList<SortUiItem> {
        blocks.clear()
        for (i in 0..9) {
            val num = (0..20).random()
            blocks.add(
                SortUiItem(
                    id = i,
                    value = num,
                    color = Color(
                        red = (0..255).random(),
                        green = (0..255).random(),
                        blue = 255,
                        alpha = 255
                    )
                )
            )
        }
        return blocks
    }

    fun sort() {
        viewModelScope.launch {
            sortUseCase.sort(blocks.map { sortUiItem -> sortUiItem.value }.toMutableList())
                .collect { sortEvent ->
                    val index = sortEvent.currentItemIndex
                    blocks[index] = blocks[index].copy(comparingActive = true)
                    blocks[index + 1] = blocks[index + 1].copy(comparingActive = true)

                    if (sortEvent.shouldSwap) {
                        val temp = blocks[index].copy(comparingActive = false, swapped = true)
                        blocks[index] = blocks[index + 1].copy(comparingActive = false)
                        blocks[index + 1] = temp
                        delay(500)

                        blocks[index + 1] =
                            blocks[index + 1].copy(comparingActive = false, swapped = false)
                    }

                    if (sortEvent.skipped) {
                        blocks[index] = blocks[index].copy(comparingActive = false, swapped = false)
                        blocks[index + 1] =
                            blocks[index + 1].copy(comparingActive = false, swapped = false)
                    }
                }
        }
    }
}