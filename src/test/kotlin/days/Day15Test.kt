package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Ignore
import org.junit.Test

class Day15Test {

    private val dayFifteen = Day15()

    @Test
    fun testPartOne() {
        assertThat(dayFifteen.partOne(), `is`(436))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayFifteen.partTwo(), `is`(175594))
    }

    @Test
    fun testPartOneExamples() {
        assertThat(Day15.MemoryGame1(listOf(0, 3, 6)).simulateUntil(2020), `is`(436))
        assertThat(Day15.MemoryGame1(listOf(1, 3, 2)).simulateUntil(2020), `is`(1))
        assertThat(Day15.MemoryGame1(listOf(2, 1, 3)).simulateUntil(2020), `is`(10))
        assertThat(Day15.MemoryGame1(listOf(1, 2, 3)).simulateUntil(2020), `is`(27))
        assertThat(Day15.MemoryGame1(listOf(2, 3, 1)).simulateUntil(2020), `is`(78))
        assertThat(Day15.MemoryGame1(listOf(3, 2, 1)).simulateUntil(2020), `is`(438))
        assertThat(Day15.MemoryGame1(listOf(3, 1, 2)).simulateUntil(2020), `is`(1836))

        assertThat(Day15.MemoryGame2(listOf(0, 3, 6)).simulateUntil(2020), `is`(436))
        assertThat(Day15.MemoryGame2(listOf(1, 3, 2)).simulateUntil(2020), `is`(1))
        assertThat(Day15.MemoryGame2(listOf(2, 1, 3)).simulateUntil(2020), `is`(10))
        assertThat(Day15.MemoryGame2(listOf(1, 2, 3)).simulateUntil(2020), `is`(27))
        assertThat(Day15.MemoryGame2(listOf(2, 3, 1)).simulateUntil(2020), `is`(78))
        assertThat(Day15.MemoryGame2(listOf(3, 2, 1)).simulateUntil(2020), `is`(438))
        assertThat(Day15.MemoryGame2(listOf(3, 1, 2)).simulateUntil(2020), `is`(1836))
    }

    @Test
    @Ignore("Passes but takes ~17s")
    fun testPartTwoExamples() {
        assertThat(Day15.MemoryGame2(listOf(0, 3, 6)).simulateUntil(30000000), `is`(175594))
        assertThat(Day15.MemoryGame2(listOf(1, 3, 2)).simulateUntil(30000000), `is`(2578))
        assertThat(Day15.MemoryGame2(listOf(2, 1, 3)).simulateUntil(30000000), `is`(3544142))
        assertThat(Day15.MemoryGame2(listOf(1, 2, 3)).simulateUntil(30000000), `is`(261214))
        assertThat(Day15.MemoryGame2(listOf(2, 3, 1)).simulateUntil(30000000), `is`(6895259))
        assertThat(Day15.MemoryGame2(listOf(3, 2, 1)).simulateUntil(30000000), `is`(18))
        assertThat(Day15.MemoryGame2(listOf(3, 1, 2)).simulateUntil(30000000), `is`(362))
    }
}
