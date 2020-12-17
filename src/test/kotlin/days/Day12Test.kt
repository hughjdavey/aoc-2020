package days

import Coord2
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test
import java.io.File

class Day12Test {

    private val dayTwelve = Day12()
    private val instructions = File(javaClass.classLoader.getResource("input_day_12.txt").toURI()).readLines().map { Day12.Instruction(it.first(), it.drop(1).toInt()) }

    @Test
    fun testPartOne() {
        assertThat(dayTwelve.partOne(), `is`(25))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayTwelve.partTwo(), `is`(286))
    }

    @Test
    fun testPartOneExample() {
        val ship = Day12.Ship(false)
        assertThat(ship.position, `is`(Coord2(0, 0)))
        assertThat(ship.respond(instructions[0]).position, `is`(Coord2(10, 0)))
        assertThat(ship.respond(instructions[1]).position, `is`(Coord2(10, 3)))
        assertThat(ship.respond(instructions[2]).position, `is`(Coord2(17, 3)))
        assertThat(ship.respond(instructions[3]).position, `is`(Coord2(17, 3)))
        assertThat(ship.respond(instructions[4]).position, `is`(Coord2(17, -8)))
    }

    @Test
    fun testPartTwoExample() {
        val ship = Day12.Ship(true)
        assertThat(ship.position, `is`(Coord2(0, 0)))
        assertThat(ship.respond(instructions[0]).position, `is`(Coord2(100, 10)))
        assertThat(ship.respond(instructions[1]).position, `is`(Coord2(100, 10)))
        assertThat(ship.respond(instructions[2]).position, `is`(Coord2(170, 38)))
        assertThat(ship.respond(instructions[3]).position, `is`(Coord2(170, 38)))
        assertThat(ship.respond(instructions[4]).position, `is`(Coord2(214, -72)))
    }
}
