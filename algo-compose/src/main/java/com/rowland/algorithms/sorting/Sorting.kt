package com.rowland.algorithms.sorting

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.node.modifierElementOf
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rowland.algorithms.ui.theme.AlgorithmAndDataStructureTheme
import kotlinx.coroutines.launch

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


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SortingView(viewModel: SortingViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiary)
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row {
            Button(onClick = { viewModel.sort() }) {
                Text(text = "Sort list", fontWeight = FontWeight.Bold, fontSize = 22.sp)
            }
            Spacer(modifier = Modifier.padding(10.dp))

            Button(onClick = { viewModel.createRandomBlocks() }) {
                Text(text = "Random", fontWeight = FontWeight.Bold, fontSize = 22.sp)
            }
        }

        Spacer(modifier = Modifier.padding(10.dp))

        LazyColumn(
            contentPadding = PaddingValues(2.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                items(viewModel.blocks) { block ->
                    val borderStoke = if (block.comparingActive) {
                        BorderStroke(width = 3.dp, Color.White)
                    } else {
                        BorderStroke(width = 3.dp, Color.Transparent)
                    }

                    Box(
                        modifier = Modifier
                            .size(if (block.swapped) (60*1.1).dp else 60.dp)
                            .padding(4.dp)
                            .background(block.color, RoundedCornerShape(15.dp))
                            .border(borderStoke, RoundedCornerShape(15.dp))
                            .animateContentSize(  tween(500))
                            .animateItemPlacement(tween(500)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = block.value.toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        )
                    }
                }
            })
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
            val viewModel = SortingViewModel(BubbleSortUseCase())
            SortingView(viewModel)
        }
    }
}