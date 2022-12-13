package com.rowland.algorithms

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rowland.algorithms.sorting.*
import com.rowland.algorithms.ui.theme.AlgorithmAndDataStructureTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlgorithmAndDataStructureTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val blocks = remember {mutableStateOf(createRandomBlocks()) }

                    val bubbleSortViewModel = SortingViewModel(BubbleSortUseCase(), blocks.value)
                    val quickSortViewModel = SortingViewModel(BubbleSortUseCase(), blocks.value)
                    val selectionSortViewModel = SortingViewModel(BubbleSortUseCase(), blocks.value)

                    fun sort() {
                        bubbleSortViewModel.sort()
                        quickSortViewModel.sort()
                        selectionSortViewModel.sort()
                    }


                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Row {
                            Button(onClick = { sort() }) {
                                Text(
                                    text = "Sort blocks",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                            }
                            Spacer(modifier = Modifier.padding(10.dp))

                            Button(onClick = { blocks.value = createRandomBlocks() }) {
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
                            selectionSortViewModel = selectionSortViewModel
                        )
                    }
                }
            }
        }
    }
}