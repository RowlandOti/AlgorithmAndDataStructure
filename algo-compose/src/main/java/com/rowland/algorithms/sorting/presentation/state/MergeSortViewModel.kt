package com.rowland.algorithms.sorting.presentation.state

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rowland.algorithms.sorting.domain.usecases.MergeSortUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MergeSortViewModel(
    private val sortUseCase: MergeSortUseCase,
    private val blocks: MutableList<Int>
) : ViewModel() {

    val sortedBlocks = mutableStateListOf<MergeSortUiItem>()
    private var job: Job? = null

    fun sort() {
        sortedBlocks.clear()
        subscribe()
        viewModelScope.launch {
            sortUseCase.sort(blocks, 0)

        }
    }

    private fun subscribe() {
        job?.cancel()
        job = viewModelScope.launch {
            sortUseCase.sortSharedFlow.collect { sortEvent ->
                val depthExistsListIndex = sortedBlocks.indexOfFirst {
                    it.depth == sortEvent.depth && it.sortState == sortEvent.sortState
                }

                if (depthExistsListIndex == -1) {
                    val mergeSortUiItem = MergeSortUiItem(
                        depth = sortEvent.depth,
                        sortParts = listOf(sortEvent.sortParts),
                        sortState = sortEvent.sortState,
                        color = Color(
                            red = (0..255).random(),
                            blue = (0..255).random(),
                            green = (0..255).random(),
                            alpha = 255
                        )
                    )
                    sortedBlocks.add(mergeSortUiItem)
                } else {
                    val currPartsList = sortedBlocks[depthExistsListIndex].sortParts.toMutableList()
                    currPartsList.add(sortEvent.sortParts)

                    sortedBlocks[depthExistsListIndex] = sortedBlocks[depthExistsListIndex].copy(sortParts = currPartsList)
                }

                sortedBlocks.sortedWith(compareBy({ it.sortState.id }, { it.depth }))
            }
        }

    }
}