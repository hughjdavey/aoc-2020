package days

class Day11 : Day(11) {

    private val layout = SeatLayout(inputList)

    // 2166
    override fun partOne(): Any {
        return applyUntilNoChange(SeatLayout::applyRules).numberOccupied()
    }

    // 1955
    override fun partTwo(): Any {
        return applyUntilNoChange(SeatLayout::applyCorrectedRules).numberOccupied()
    }

    private fun applyUntilNoChange(fn: (s: SeatLayout) -> SeatLayout): SeatLayout {
        return generateSequence(layout) { layout ->
            val newLayout = fn(layout)
            if (layout.toString() == newLayout.toString()) null else newLayout
        }.last()
    }

    class SeatLayout(private val rows: List<String>) {

        private val maxX = rows.first().lastIndex
        private val maxY = rows.lastIndex

        fun applyRules() = applyRules(false, 4)

        fun applyCorrectedRules() = applyRules(true, 5)

        private fun applyRules(adjacentSeats: Boolean, occupiedThreshold: Int): SeatLayout {
            val newRows = (0..maxY).flatMap { y -> (0..maxX).map { x ->
                val seat = get(x, y)
                val adjacent = if (adjacentSeats) getAdjacentSeats(x, y) else getAdjacent(x, y)

                if (seat == 'L' && adjacent.none { it == '#' }) '#'
                else if (seat == '#' && adjacent.count { it == '#' } >= occupiedThreshold) 'L'
                else get(x, y)
            } }.chunked(maxX + 1).map { it.joinToString("") }
            return SeatLayout(newRows)
        }

        fun get(x: Int, y: Int): Char {
            if (y < 0 || y > maxY || x < 0 || x > maxX) {
                return ' '
            }
            return rows[y][x]
        }

        private fun get(xy: Pair<Int, Int>): Char {
            return get(xy.first, xy.second)
        }

        fun getAdjacent(x: Int, y: Int): List<Char> {
            return directionFunctions.map { it(x, y) }
                .map { get(it.first, it.second) }.filterNot { it == ' ' }
        }

        fun getAdjacentSeats(x: Int, y: Int): List<Char> {
            // doing as a sequence inspired by https://github.com/paulBee/AdventOfKotlin/blob/master/src/main/kotlin/year2020/day11.kt
            return directionFunctions.map { f ->
                generateSequence(f(x, y)) { coord -> f(coord.first, coord.second) }.map { get(it) }.find { it != '.' }!!
            }.filterNot { it == ' ' }
        }

        fun numberOccupied(): Int {
            return rows.flatMap { r -> r.map { c -> if (c == '#') 1 else 0 } }.sum()
        }

        override fun toString(): String {
            return rows.joinToString("\n")
        }

        companion object {

            // inspired by `allDirections` in https://github.com/paulBee/AdventOfKotlin/blob/master/src/main/kotlin/year2020/day11.kt
            // resolves to a list of functions for each of the 8 possible adjacent moves
            val directionFunctions = (-1..1).flatMap { y -> (-1..1).map { x -> x to y } }
                .filterNot { (x, y) -> x == 0 && y == 0 }
                .map { (dx, dy) -> { x: Int, y: Int -> x + dx to y + dy } }
        }
    }
}
