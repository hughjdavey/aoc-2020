package days

class Day10 : Day(10) {

    private val ratings = inputList.map { it.toInt() }.let {
        val outletJoltage = 0
        val deviceJoltage = (it.maxOrNull() ?: 0) + 3
        it.plus(outletJoltage).plus(deviceJoltage)
    }.sorted()

    // 2450
    override fun partOne(): Any {
        return countJoltDifferences(1) * countJoltDifferences(3)
    }

    // 32396521357312
    override fun partTwo(): Any {
        return countAdapterArrangementsChunked()
    }

    fun countJoltDifferences(diff: Int): Int {
        return ratings.windowed(2).count { it.last() - it.first() == diff }
    }

    // works on test inputs but too slow for real input
    fun countAdapterArrangements(next: Int = 0, count: Int = 0): Int {
        if (next >= ratings.maxOrNull()!!) {
            return count + 1
        }
        val nexts = listOf(next + 1, next + 2, next + 3).filter { ratings.contains(it) }
        return count + nexts.map { countAdapterArrangements(it, count) }.sum()
    }

    // inspired by https://www.reddit.com/r/adventofcode/comments/kacdbl/2020_day_10c_part_2_no_clue_how_to_begin/gf9lzhd/
    fun countAdapterArrangementsChunked(): Long {
        val ratingsToPaths = ratings.mapIndexed { i, n -> if (i == 0) RatingToPath(n, 1L) else RatingToPath(n) }.toList()
        ratingsToPaths.forEach { r2p ->
            r2p.reachableRatings().forEach { rating -> ratingsToPaths.ifExistsWithRating(rating) { it.paths += r2p.paths } }
        }
        return ratingsToPaths.last().paths
    }

    private data class RatingToPath(val rating: Int, var paths: Long = 0L) {

        fun reachableRatings() = (1..3).map { rating + it }
    }

    private fun List<RatingToPath>.ifExistsWithRating(rating: Int, f: (RatingToPath) -> Unit) {
        val maybe = this.find { it.rating == rating }
        if (maybe != null) {
            f(maybe)
        }
    }
}
