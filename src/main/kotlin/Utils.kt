fun <T> List<T>.allPairs(): List<Pair<T, T>> = this.flatMap { i -> this.map { j -> Pair(i, j) } }

fun <T> List<T>.allTriples(): List<Triple<T, T, T>> = this.flatMap { i -> this.flatMap { j ->  this.map { k -> Triple(i, j, k) } } }

fun Pair<Int, Int>.add() = this.first + this.second

fun Pair<Int, Int>.mul() = this.first * this.second

fun Triple<Int, Int, Int>.add() = this.first + this.second + this.third

fun Triple<Int, Int, Int>.mul() = this.first * this.second * this.third
