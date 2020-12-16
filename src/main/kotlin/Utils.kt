import kotlin.math.abs

fun <T> List<T>.allPairs(): List<Pair<T, T>> = this.flatMap { i -> this.map { j -> Pair(i, j) } }

fun <T> List<T>.allTriples(): List<Triple<T, T, T>> = this.flatMap { i -> this.flatMap { j ->  this.map { k -> Triple(i, j, k) } } }

fun Pair<Int, Int>.add() = this.first + this.second

fun Pair<Int, Int>.mul() = this.first * this.second

fun Triple<Int, Int, Int>.add() = this.first + this.second + this.third

fun Triple<Int, Int, Int>.mul() = this.first * this.second * this.third

fun String.isIntAndInRange(start: Int, end: Int): Boolean {
    val maybeInt = this.toIntOrNull() ?: return false
    return maybeInt in start..end
}

enum class Direction {
    NORTH, EAST, SOUTH, WEST;

    fun rotate(degrees: Int): Direction {
        if (abs(degrees) !in listOf(0, 90, 180, 270, 360)) {
            throw IllegalArgumentException("Valid rotations are +/- 0, 90, 180, 270 and 360")
        }
        var newIndex = values().indexOf(this) + (degrees / 90)
        while (newIndex < 0) newIndex += 4
        return values()[newIndex % 4]
    }
}

data class Coord(val x: Int, val y: Int) {

    fun plusX(delta: Int) = copy(x = x + delta)
    fun minusX(delta: Int) = copy(x = x - delta)
    fun plusY(delta: Int) = copy(y = y + delta)
    fun minusY(delta: Int) = copy(y = y - delta)

    fun absSum() = abs(x) + abs(y)

    fun diff(from: Coord = Coord(0, 0)): Coord {
        return Coord(x - from.x, y - from.y)
    }

    fun manhattan(from: Coord = Coord(0, 0)): Int {
        return diff(from).absSum()
    }

    fun rotate(axis: Coord, degrees: Int): Coord {
        val diff: Coord by lazy { diff(axis) }
        return when (degrees) {
            360, -360 -> copy()
            180, -180 -> axis.minusX(diff.x).minusY(diff.y)
            90, -270 -> copy(x = axis.x + diff.y, y = axis.y - diff.x)
            -90, 270 -> copy(x = axis.x - diff.y, y = axis.y + diff.x)
            else -> throw IllegalArgumentException("Valid rotations are +/- 0, 90, 180, 270 and 360")
        }
    }
}

fun List<String>.splitOnBlank(): List<List<String>> {
    val lists = mutableListOf(mutableListOf<String>())
    for (str in this) {
        if (str.isBlank()) {
            lists.add(mutableListOf())
        }
        else {
            lists.last().add(str)
        }
    }
    return lists
}
