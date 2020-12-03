package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsIterableContainingInOrder.contains
import org.hamcrest.core.Is.`is`
import org.junit.Test
import java.io.File

class Day3Test {

    private val dayThree = Day3()
    private val inputList = File(javaClass.classLoader.getResource("input_day_3.txt").toURI()).readLines().map { it.trim() }

    @Test
    fun testPartOne() {
        assertThat(dayThree.partOne(), `is`(7))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayThree.partTwo(), `is`(336))
    }

    @Test
    fun testTreeGridPatternRepeating() {
        val tg = Day3.TreeGrid(inputList)
        val secondLineFromWeb = "#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#.."
        val secondLineFromTg = (secondLineFromWeb.indices).map { x -> tg.get(x, 1) }.joinToString("")
        assertThat(secondLineFromTg, `is`(secondLineFromWeb))
    }

    @Test
    fun testTreeGridPathing() {
        val tg = Day3.TreeGrid(inputList)
        val path = tg.path(3, 1)
        assertThat(path, contains('.', '.', '#', '.', '#', '#', '.', '#', '#', '#', '#'))
    }
}
