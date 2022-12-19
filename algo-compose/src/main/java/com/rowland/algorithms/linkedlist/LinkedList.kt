package com.rowland.algorithms.linkedlist

fun main(args: Array<String>) {
    val linkedList = LinkedList()

    for (i in 0..7) {
        val num = (0..20).random()
        linkedList.add(num)
    }

    println("Unsorted LinkedList: $linkedList")
    //val sortedBlocks = mergeSort(linkedList)
    println("Sorted  LinkedList: $linkedList")
    linkedList.add(66)
    println("Added   LinkedList: $linkedList")
    linkedList.addAtIndex(99, 6)
    println("Added@t LinkedList: $linkedList")
    println("Search  LinkedList: ${linkedList.search(99).toString()}")
    println("Search  LinkedList: ${linkedList.search(199).toString()}")
    linkedList.head = linkedList.reverse(linkedList.head)
    println("Reverse  LinkedList: $linkedList")
    println("Sort  LinkedList: ${linkedList.sort(linkedList.head!!).toString()}")
}

class LinkedList(var head: Node? = null) {

    fun add(num: Int) {
        if (head == null) {
            head = Node(num)
            return
        }

        var curr = head

        while (curr?.next != null) {
            curr = curr.next
        }

        curr?.next = Node(num)
    }

    fun addAtIndex(num: Int, index: Int) {
        var curr = head
        var currIndex = 0
        var prev: Node? = null

        while (curr != null) {
            if (currIndex == index) {
                break
            }

            prev = curr
            curr = curr.next
            currIndex += 1
        }

        val node = Node(num)

        if (prev != null) {
            prev.next = node
        }

        node.next = curr
    }

    fun remove(num: Int) {
        var curr = head
        var prev: Node? = null

        if (curr != null) {
            if (curr.value == num) {
                head = null

                return
            }
        }

        while (curr != null) {
            if (curr.value == num) {
                if (prev != null) {
                    prev.next = curr.next
                }
            }

            prev = curr
            curr = curr.next
        }
    }

    fun search(num: Int): Node? {
        var curr = head

        while (curr != null) {
            if (curr.value == num) {
                return curr
            }

            curr = curr.next
        }

        return null
    }

    fun reverse(node: Node?, prev: Node? = null): Node? {

        if (node == null) {
            return prev
        }

        val next = node.next
        node.next = prev

        return reverse(next, node)
    }

    fun sort(node: Node): Node {
        if (node.next == null) {
            return node
        }

        val middle = findMiddle(node)
        val nextMiddle = middle.next
        middle.next = null

        val left = sort(node)
        val right = nextMiddle?.let { sort(it) }

        return merge(left, right)
    }

    private fun findMiddle(node: Node): Node {
        var slow = node
        var fast = node

        while (fast.next != null && fast.next?.next != null) {
            slow = slow.next!!
            fast = fast.next?.next!!
        }

        return slow
    }

    private fun merge(left: Node?, right: Node?): Node {
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

        return root.next!!
    }

    override fun toString(): String {
        var curr = head
        var representation = ""

        while (curr != null) {
            representation += "${curr.value}-->"
            curr = curr.next
        }

        representation += "null"

        return representation
    }

}