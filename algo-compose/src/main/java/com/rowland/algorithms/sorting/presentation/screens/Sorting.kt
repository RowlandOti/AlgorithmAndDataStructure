package com.rowland.algorithms.sorting.presentation.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rowland.algorithms.sorting.domain.usecases.*
import com.rowland.algorithms.sorting.presentation.state.SortUiItem
import com.rowland.algorithms.sorting.presentation.state.SortingViewModel
import com.rowland.algorithms.ui.theme.AlgorithmAndDataStructureTheme

fun main(args: Array<String>) {
    val blocks = mutableListOf<Int>()
    for (i in 0..7) {
        val num = (0..20).random()
        blocks.add(num)
    }

    println("Unsorted Array: $blocks")
    val sortedBlocks = bubbleSort(blocks)
    println("Sorted Array: $sortedBlocks")
}

fun bubbleSort(blocks: MutableList<Int>) {
    for (outerIndex in 0 until blocks.size - 1) {
        for (innerIndex in 0 until blocks.size - 1 - outerIndex) {
            if (blocks[innerIndex] > blocks[innerIndex + 1]) {
                swap(blocks, innerIndex, innerIndex + 1)
            }
        }
        println("Loop $outerIndex:  ${blocks.toList()}")
    }
}

fun quickSort(blocks: MutableList<Int>, start: Int, end: Int) {
    if (start < end) {
        val pivotIndex = partition(blocks, start, end)
        quickSort(blocks, start, pivotIndex - 1)
        quickSort(blocks, pivotIndex + 1, end)
    }
}

fun partition(blocks: MutableList<Int>, start: Int, end: Int): Int {
    val pivot = blocks[end]
    // index of smaller element
    var i = start
    for (j in start until end) {
        if (blocks[j] <= pivot) {
            // All elements < pivot moved to the left
            swap(blocks, i, j)
            i++
        }
    }
    // move pivot to correct position
    swap(blocks, i, end)
    return i
}

fun createRandomBlocks(): MutableList<SortUiItem> {
    val blocks = mutableListOf<SortUiItem>()
    for (i in 0..7) {
        val num = (0..50).random()
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

@Composable
fun Sorting() {
    val blocks = createRandomBlocks().toMutableStateList()
    val blocksCopyOne = remember { blocks.toMutableStateList() }
    val blocksCopyTwo = remember { blocks.toMutableStateList() }
    val blocksCopyThree = remember { blocks.toMutableStateList() }
    val blocksCopyFour = remember { blocks.toMutableStateList() }

    val bubbleSortViewModel = SortingViewModel(BubbleSortUseCase(), blocksCopyOne)
    val quickSortViewModel = SortingViewModel(QuickSortUseCase(), blocksCopyTwo)
    val selectionSortViewModel = SortingViewModel(SelectionSortUseCase(), blocksCopyThree)
    val insertionSortViewModel = SortingViewModel(InsertionSortUseCase(), blocksCopyFour)

    fun randomize() {
        blocks.clear()
        blocksCopyOne.clear()
        blocksCopyTwo.clear()
        blocksCopyThree.clear()
        blocksCopyFour.clear()

        blocks.addAll(createRandomBlocks().toMutableStateList())

        blocksCopyOne.addAll(blocks)
        blocksCopyTwo.addAll(blocks)
        blocksCopyThree.addAll(blocks)
        blocksCopyFour.addAll(blocks)

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row {
            Button(onClick = {
                bubbleSortViewModel.sort()
                quickSortViewModel.sort()
                selectionSortViewModel.sort()
                insertionSortViewModel.sort()
            }) {
                Text(
                    text = "Sort blocks",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))

            Button(onClick = { randomize() }) {
                Text(
                    text = "Random",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }

        SortingView(
            bubbleSortViewModel = bubbleSortViewModel,
            quickSortViewModel = quickSortViewModel,
            selectionSortViewModel = selectionSortViewModel,
            insertionSortViewModel = insertionSortViewModel
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SortingView(
    bubbleSortViewModel: SortingViewModel,
    quickSortViewModel: SortingViewModel,
    selectionSortViewModel: SortingViewModel,
    insertionSortViewModel: SortingViewModel
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.padding(5.dp))

        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
            SortingColumn(blocks = bubbleSortViewModel.blocks, header = "Bubble")
            SortingColumn(blocks = quickSortViewModel.blocks, header = "Quick")
            SortingColumn(blocks = selectionSortViewModel.blocks, header = "Selection")
            SortingColumn(blocks = insertionSortViewModel.blocks, header = "Insertion")
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SortingColumn(blocks: List<SortUiItem>, header: String) {
    LazyColumn(
        modifier = Modifier.fillMaxHeight(),
        contentPadding = PaddingValues(2.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            item {
                Text(text = header, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            itemsIndexed(items = blocks, key = { _, block -> block.id }) { _, block ->
                AnimatedBox(
                    block = block,
                    modifier = Modifier.animateItemPlacement(animationSpec = tween(500))
                )
            }
        })
}

@Composable
fun AnimatedBox(block: SortUiItem, modifier: Modifier) {
    val borderStoke = if (block.comparingActive) {
        BorderStroke(width = 3.dp, Color.White)
    } else {
        BorderStroke(width = 3.dp, Color.Transparent)
    }

    Box(
        modifier = modifier
            .size(60.dp)
            .scale(if (block.swapped) 1.2f else 1f)
            .padding(4.dp)
            .background(block.color, RoundedCornerShape(15.dp))
            .border(borderStoke, RoundedCornerShape(15.dp))
            .animateContentSize(animationSpec = tween(500)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = block.value.toString(),
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
        )
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
fun SortingViewPreview() {
    AlgorithmAndDataStructureTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {

            val blocks = remember { (createRandomBlocks()) }

            SortingView(
                bubbleSortViewModel = SortingViewModel(BubbleSortUseCase(), blocks),
                quickSortViewModel = SortingViewModel(BubbleSortUseCase(), blocks),
                selectionSortViewModel = SortingViewModel(SelectionSortUseCase(), blocks),
                insertionSortViewModel = SortingViewModel(InsertionSortUseCase(), blocks)
            )
        }
    }
}