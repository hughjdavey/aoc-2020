package days

import Coord
import Coord3
import Coord4

class Day17 : Day(17) {

    // 401
    override fun partOne(): Any {
        val initialCoords = inputList.mapIndexed { y, s -> s.mapIndexedNotNull { x, c -> if (c == '#') Coord3(x, y, 0) else null } }.flatten()
        val dimension = PocketDimension(initialCoords)
        repeat(6) { dimension.cycle() }
        return ConwayNCube.allNCubes.count { it.active }
    }

    // 2224
    // todo this take almost 7 minutes to run - improve algorithm!
    override fun partTwo(): Any {
        val initialCoords = inputList.mapIndexed { y, s -> s.mapIndexedNotNull { x, c -> if (c == '#') Coord4(x, y, 0, 0) else null } }.flatten()
        val dimension = PocketDimension(initialCoords)
        repeat(6) { dimension.cycle() }
        return ConwayNCube.allNCubes.count { it.active }
    }

    class PocketDimension(initial: List<Coord>) {

        init {
            ConwayNCube.allNCubes = initial.map { ConwayNCube(it, true) }.toMutableSet()
        }

        fun cycle() {
            ConwayNCube.grow()
            val cubes = ConwayNCube.allNCubes.toList()
            cubes.forEach { it.doCycle() }
            cubes.forEach { it.update() }
            ConwayNCube.allNCubes = cubes.filter { it.active }.toMutableSet()
        }
    }

    data class ConwayNCube(val coord: Coord, var active: Boolean) {

        var pendingChange: ((ConwayNCube) -> Unit)? = null

        fun update() = pendingChange?.let { it(this) }

        fun doCycle() {
            val activeNeighbours = getNeighbours().count { it.active }
            if (active && (activeNeighbours == 2 || activeNeighbours == 3)) {
                pendingChange = { it.active = true }
            }
            else if (!active && activeNeighbours == 3) {
                pendingChange = { it.active = true }
            }
            else {
                pendingChange = { it.active = false }
            }
        }

        private fun getNeighbours(): List<ConwayNCube> {
            return coord.getAdjacent().map { adj ->
                allNCubes.find { it.coord == adj } ?: addCube(ConwayNCube(adj, false))
            }
        }

        companion object {

            lateinit var allNCubes: MutableSet<ConwayNCube>

            fun addCube(cube: ConwayNCube): ConwayNCube {
                allNCubes.add(cube)
                return cube
            }

            fun grow() {
                val neighbours = allNCubes.map { c -> c.coord.getAdjacent().map { ConwayNCube(it, false) } }.flatten()
                allNCubes.addAll(neighbours)
            }
        }
    }
}
