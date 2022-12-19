package com.rowland.algorithms.linkedlist.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rowland.algorithms.linkedlist.LinkedList
import com.rowland.algorithms.linkedlist.Node
import com.rowland.algorithms.linkedlist.domain.usecases.MergeSortLinkedListUseCase
import com.rowland.algorithms.linkedlist.presentation.state.MergeSortLinkedListViewModel
import com.rowland.algorithms.sorting.presentation.state.SortUiItem
import com.rowland.algorithms.ui.theme.AlgorithmAndDataStructureTheme

fun createRandomLinkedList(): MutableList<SortUiItem> {
    val blocks = mutableListOf<SortUiItem>()
    for (i in 0..6) {
        val num = (0..9).random()
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
fun MergeSortingLinkedList() {
    val blocks = remember { createRandomLinkedList().toMutableStateList() }

    val linkedList = LinkedList()
    blocks.forEach { linkedList.add(it.value) }

    val mergeSortViewModel =
        MergeSortLinkedListViewModel(MergeSortLinkedListUseCase(), linkedList.head!!)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiary)
            .padding(5.dp)
    ) {

        LazyColumn(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxSize()
                .align(Alignment.TopCenter),
            contentPadding = PaddingValues(0.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Column() {
                    Text(
                        text = "Merge Sort",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )

                    Divider(
                        Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                    )
                }
            }

            itemsIndexed(
                mergeSortViewModel.sortedBlocks,
                key = { _, it -> it.guid }) { index, sortUiItem ->
                val depthParts = sortUiItem.sortParts

                if (index == 0) {
                    Text(
                        text = "Dividing",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }

                if (index == blocks.size / 2) {
                    Text(
                        text = "Merging",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    for (part in depthParts) {

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .padding(start = if (depthParts.indexOf(part) == 0) 0.dp else 10.dp)
                                .background(sortUiItem.color, RoundedCornerShape(8.dp))
                                .padding(5.dp)
                        ) {
                            var currPart: Node? = part

                            while (currPart != null) {

                                if (currPart.next != null) {
                                    Text(
                                        text = "${currPart.value} |",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp,
                                        color = Color.White
                                    )
                                } else {
                                    Text(
                                        text = "${currPart.value}",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp,
                                        color = Color.White
                                    )
                                }
                                currPart = currPart.next
                            }
                        }
                    }

                }

            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(10.dp)
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Text(
                text = "${blocks.map { it -> it.value }.toMutableList()}",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.padding(5.dp))

            Row {
                Button(onClick = { mergeSortViewModel.sort() }) {
                    Text(
                        text = "Sort blocks",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
                Spacer(modifier = Modifier.padding(10.dp))

                Button(onClick = {
                    blocks.clear()
                    blocks.addAll(createRandomLinkedList().toMutableStateList())
                }) {
                    Text(
                        text = "Random",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
fun MergeSortingLinkedListPreview() {
    AlgorithmAndDataStructureTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            MergeSortingLinkedList()
        }
    }
}