package com.rowland.algorithms.linkedlist.domain.usecases

import com.rowland.algorithms.linkedlist.Node
import com.rowland.algorithms.sorting.domain.MergeSortState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow

class MergeSortLinkedListUseCase {

    val sortSharedFlow = MutableSharedFlow<MergeSortLinkedListEvent>()

    suspend fun sort(head: Node, depth: Int): Node {
        delay(500)
        sortSharedFlow.emit(
            MergeSortLinkedListEvent(
                depth = depth,
                sortParts = head,
                sortState = MergeSortState.DividedState()
            )
        )

        if (head.next == null) {
            return head
        }

        val middle = findMiddle(head)
        val nextMiddle = middle.next
        middle.next = null

        val left = sort(head, depth + 1)
        val right = nextMiddle?.let { sort(it, depth + 1) }

        return merge(left, right, depth)
    }

    private suspend fun findMiddle(head: Node): Node {
        var slow = head
        var fast = head

        while (fast.next != null && fast.next?.next != null) {
            slow = slow.next!!
            fast = fast.next?.next!!
        }

        return slow
    }

    private suspend fun merge(left: Node?, right: Node?, depth: Int): Node {
        var currLeft = left
        var currRight = right
        val root = Node(-1)
        var curr = root

        while (currLeft != null && currRight != null) {
            if (currLeft.value < currRight.value) {
                val node = Node(currLeft.value)
                curr.next = node
                curr = node
                currLeft = currLeft.next
            } else {
                val node = Node(currRight.value)
                curr.next = node
                curr = node
                currRight = currRight.next
            }
        }

        while (currRight != null) {
            val node = Node(currRight.value)
            curr.next = node
            curr = node

            currRight = currRight.next
        }

        while (currLeft != null) {
            val node = Node(currLeft.value)
            curr.next = node
            curr = node

            currLeft = currLeft.next
        }

        delay(500)
        sortSharedFlow.emit(
            MergeSortLinkedListEvent(
                depth = depth,
                sortParts = root.next!!,
                sortState = MergeSortState.MergedState()
            )
        )

        return root.next!!
    }
}
