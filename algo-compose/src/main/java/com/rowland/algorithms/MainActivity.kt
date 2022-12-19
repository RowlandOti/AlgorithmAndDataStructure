package com.rowland.algorithms

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rowland.algorithms.linkedlist.presentation.screens.MergeSortingLinkedList
import com.rowland.algorithms.ui.theme.AlgorithmAndDataStructureTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlgorithmAndDataStructureTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.tertiary,
                ) {
                    MergeSortingLinkedList()
                }
            }
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
fun SortingViewPreview() {
    AlgorithmAndDataStructureTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.tertiary,
        ) {
            MergeSortingLinkedList()
        }
    }
}