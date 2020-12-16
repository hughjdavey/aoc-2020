package days

import stackOf
import java.util.Stack

class Day15 : Day(15) {

    private val startingNumbers = inputString.split(",").map { it.trim().toInt() }

    // 273
    override fun partOne(): Any {
        return MemoryGame1(startingNumbers).simulateUntil(2020)
    }

    // 47205
    override fun partTwo(): Any {
        return MemoryGame2(startingNumbers).simulateUntil(30000000)
    }

    abstract class MemoryGame(protected val startingStack: Stack<Int>) {

        abstract fun turn(turn: Int)

        abstract fun last(): Int

        fun simulateUntil(turn: Int): Int {
            for (i in 1 until turn + 1) {
                turn(i)
            }
            return last()
        }
    }

    // works on test inputs but too slow for real input
    class MemoryGame1(startingNumbers: List<Int>) : MemoryGame(stackOf(startingNumbers)) {

        private val numbersSpoken: MutableList<Int> = mutableListOf()

        override fun last() = numbersSpoken.last()

        override fun turn(turn: Int) {
            if (!startingStack.empty()) numbersSpoken.add(startingStack.pop()) else {
                val lastSpoken = numbersSpoken.last()
                val indexBeforeLast = numbersSpoken.dropLast(1).indexOfLast { it == lastSpoken }
                val next = if (indexBeforeLast == -1) 0 else turn - (indexBeforeLast + 2)
                numbersSpoken.add(next)
            }
        }
    }

    // todo fast enough to work on part 2 but still quite slow @ > 2.5s - improve
    // loosely inspired by https://www.reddit.com/r/adventofcode/comments/kdwfc8/2020_day_15_part_2_java_part_1_works_but_part_2/
    class MemoryGame2(startingNumbers: List<Int>) : MemoryGame(stackOf(startingNumbers)) {

        private val numbersSpoken: MutableMap<Int, GameNumber> = mutableMapOf()
        private lateinit var last: GameNumber

        override fun last() = last.value

        override fun turn(turn: Int) {
            val gameNum: GameNumber = if (!startingStack.empty()) GameNumber(startingStack.pop()) else {
                val next = if (last.last2 == -1) 0 else last.last - last.last2
                numbersSpoken[next] ?: GameNumber(next)
            }

            numbersSpoken[gameNum.value] = gameNum
            gameNum.speakAt(turn)
            last = gameNum
        }
    }

    data class GameNumber(val value: Int, var last: Int = -1, var last2: Int = -1) {

        fun speakAt(turn: Int) {
            last2 = last
            last = turn
        }
    }
}
