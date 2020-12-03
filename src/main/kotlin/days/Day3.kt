package days

class Day3 : Day(3) {

    private val trees = TreeGrid(inputList.map { it.trim() })

    // 237
    override fun partOne(): Any {
        return trees.path(3, 1).count { it == '#' }
    }

    // 2106818610
    override fun partTwo(): Any {
        return listOf(1 to 1, 3 to 1, 5 to 1, 7 to 1, 1 to 2)
            .map { (right, down) -> trees.path(right, down).count { it == '#' } }
            .reduce { sum, i -> sum * i }
    }

    class TreeGrid(private val grid: List<String>) {

        private val maxX = grid.first().length

        fun get(x: Int, y: Int): Char {
            return grid[y][x % maxX]
        }

        fun path(right: Int, down: Int): List<Char> {
            return generateSequence(0 to 0) { (x, y) -> if (y + down >= grid.size) null else x + right to y + down }
                .map { (x, y) -> get(x, y) }.toList()
        }
    }
}
