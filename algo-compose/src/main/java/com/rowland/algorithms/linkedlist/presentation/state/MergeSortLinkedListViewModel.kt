package com.rowland.algorithms.linkedlist.presentation.state

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rowland.algorithms.linkedlist.Node
import com.rowland.algorithms.linkedlist.domain.usecases.MergeSortLinkedListUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MergeSortLinkedListViewModel(
    private val sortUseCase: MergeSortLinkedListUseCase,
    private val head: Node
) : ViewModel() {

    val sortedBlocks = mutableStateListOf<MergeSortLinkedListUiItem>()
    private var job: Job? = null

    fun sort() {
        sortedBlocks.clear()
        subscribe()
        viewModelScope.launch {
            sortUseCase.sort(head, 0)
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
                    val mergeSortUiItem = MergeSortLinkedListUiItem(
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

                    sortedBlocks[depthExistsListIndex] =
                        sortedBlocks[depthExistsListIndex].copy(sortParts = currPartsList)
                }

                sortedBlocks.sortedWith(compareBy({ it.sortState.id }, { it.depth }))
            }
        }

    }
}