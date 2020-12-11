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
            val foo = directionFunctions.map { f ->
                var coord = f(x, y)
                var adj = get(coord)
                while (adj == '.' && adj != ' ') {
                    coord = f(coord.first, coord.second)
                    adj = get(coord)
                }
                adj
            }
            return foo.filterNot { it == ' ' }
        }

        fun numberOccupied(): Int {
            return rows.flatMap { r -> r.map { c -> if (c == '#') 1 else 0 } }.sum()
        }

        override fun toString(): String {
            return rows.joinToString("\n")
        }

        companion object {

            // left, up-left, up, up-right, right, down-right, down, down-left
            private val l = { x: Int, y: Int -> x - 1 to y }
            private val ul = { x: Int, y: Int -> x - 1 to y - 1 }
            private val u = { x: Int, y: Int -> x to y - 1 }
            private val ur = { x: Int, y: Int -> x + 1 to y - 1 }
            private val r = { x: Int, y: Int -> x + 1 to y }
            private val dr = { x: Int, y: Int -> x + 1 to y + 1 }
            private val d = { x: Int, y: Int -> x to y + 1 }
            private val dl = { x: Int, y: Int -> x - 1 to y + 1 }

            val directionFunctions = listOf(l, ul, u, ur, r, dr, d, dl)
        }
    }
}
