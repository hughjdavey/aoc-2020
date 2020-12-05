package days

class Day5 : Day(5) {

    private val seatIds = inputList.map { Seat(it).uniqueId }

    // 838
    override fun partOne(): Any {
        return seatIds.maxOrNull() ?: 0
    }

    // 714
    override fun partTwo(): Any {
        return generateSequence(seatIds.minOrNull() ?: 0) { it + 1 }.find { !seatIds.contains(it) } ?: 0
    }

    class Seat(code: String) {

        val col = calculatePosition(7, code.takeLast(3))
        val row = calculatePosition(127, code.take(7))
        val uniqueId = row * 8 + col

        companion object {

            fun calculatePosition(max: Int, code: String): Int {
                return code.fold((0..max).toList()) { range, dir ->
                    if (dir in "FL") range.take(range.size / 2) else range.takeLast(range.size / 2)
                }.first()
            }
        }
    }
}
