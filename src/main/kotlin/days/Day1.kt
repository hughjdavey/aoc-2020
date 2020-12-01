package days

import add
import allPairs
import allTriples
import mul

class Day1 : Day(1) {

    private val inputNumbers = inputList.map { it.toInt() }

    // 719796
    override fun partOne(): Any {
        return inputNumbers.allPairs().find { it.add() == 2020 }?.mul() ?: 0
    }

    // 144554112
    override fun partTwo(): Any {
        return inputNumbers.allTriples().find { it.add() == 2020 }?.mul() ?: 0
    }
}
