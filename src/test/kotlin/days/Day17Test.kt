package days

import Coord3
import Coord4
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Ignore
import org.junit.Test

class Day17Test {

    private val daySeventeen = Day17()

    @Test
    fun testPartOne() {
        assertThat(daySeventeen.partOne(), `is`(112))
    }

    @Test
    @Ignore("Passes but takes ~2m")
    fun testPartTwo() {
        assertThat(daySeventeen.partTwo(), `is`(848))
    }

    @Test
    fun testPartOneExampleMidpoints() {
        val initialCoords = listOf(Coord3(1, 0, 0), Coord3(2, 1, 0), Coord3(0, 2, 0), Coord3(1, 2, 0), Coord3(2, 2, 0))
        val dimension = Day17.PocketDimension(initialCoords)
        assertThat(Day17.ConwayNCube.allNCubes.count { it.active }, `is`(5))
        dimension.cycle()
        assertThat(Day17.ConwayNCube.allNCubes.count { it.active }, `is`(11))
        dimension.cycle()
        assertThat(Day17.ConwayNCube.allNCubes.count { it.active }, `is`(21))
        dimension.cycle()
        assertThat(Day17.ConwayNCube.allNCubes.count { it.active }, `is`(38))
    }

    @Test
    fun testPartTwoExample() {
        val initialCoords = listOf(Coord4(1, 0, 0, 0), Coord4(2, 1, 0, 0), Coord4(0, 2, 0, 0), Coord4(1, 2, 0, 0), Coord4(2, 2, 0, 0))
        val dimension = Day17.PocketDimension(initialCoords)
        assertThat(Day17.ConwayNCube.allNCubes.count { it.active }, `is`(5))
        dimension.cycle()
        assertThat(Day17.ConwayNCube.allNCubes.count { it.active }, `is`(29))
        dimension.cycle()
        assertThat(Day17.ConwayNCube.allNCubes.count { it.active }, `is`(60))
    }
}
