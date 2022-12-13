package com.rowland.algorithms.sorting

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rowland.algorithms.ui.theme.AlgorithmAndDataStructureTheme

fun main(args: Array<String>) {
    val blocks = mutableListOf<Int>()
    for (i in 0..10) {
        val num = (0..9).random()
        blocks.add(num)
    }

    println("Unsorted Array: $blocks")
    val sortedBlocks = bubbleSort(blocks)
    println("Sorted Array: $blocks")
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

fun createRandomBlocks(): MutableList<SortUiItem> {
    val blocks = mutableListOf<SortUiItem>()
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


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SortingView(
    bubbleSortViewModel: SortingViewModel,
    quickSortViewModel: SortingViewModel,
    selectionSortViewModel: SortingViewModel
) {
    Column {
        Spacer(modifier = Modifier.padding(5.dp))

        Row() {
            SortingColumn(blocks = bubbleSortViewModel.blocks, header = "Bubble")
            SortingColumn(blocks = quickSortViewModel.blocks, header = "Quick")
            SortingColumn(blocks = selectionSortViewModel.blocks, header = "Selection")
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SortingColumn(blocks: List<SortUiItem>, header: String) {
    LazyColumn(
        contentPadding = PaddingValues(2.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            item {
                Text(text = header, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            items(blocks) { block ->
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
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
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
                selectionSortViewModel = SortingViewModel(BubbleSortUseCase(), blocks)
            )
        }
    }
}