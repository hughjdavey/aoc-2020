package days

class Day9 : Day(9) {

    private val numbers = inputList.map { it.toLong() }

    // 2089807806
    override fun partOne(): Any {
        return findFirstInvalidNumber(numbers, 25)
    }

    // 245848639
    override fun partTwo(): Any {
        return findEncryptionWeakness(numbers, findFirstInvalidNumber(numbers, 25))
    }

    fun findFirstInvalidNumber(numbers: List<Long>, preambleSize: Int): Long {
        return numbers.windowed(preambleSize + 1).find {
            val (preamble, n) = it.dropLast(1) to it.last()
            preamble.none { n - it in preamble.minus(it) }
        }?.last() ?: 0
    }

    fun findEncryptionWeakness(numbers: List<Long>, invalid: Long): Long {
        val contiguousSet = findSetThatSumsTo(numbers, invalid)
        return (contiguousSet.minOrNull() ?: 0) + (contiguousSet.maxOrNull() ?: 0)
    }

    fun findSetThatSumsTo(numbers: List<Long>, invalid: Long): List<Long> {
        return generateSequence(2) { it + 1 }.flatMap { numbers.windowed(it) }
            .find { it.sum() == invalid } ?: emptyList()
    }
}
