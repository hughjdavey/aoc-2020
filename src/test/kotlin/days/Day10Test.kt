package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day10Test {

    private val dayTen = Day10()
    private val exampleOne = listOf(16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4, 0, 22).sorted()
    private val exampleTwo = listOf(28, 33, 18, 42, 31, 14, 46, 20, 48, 47, 24, 23, 49, 45, 19, 38, 39, 11, 1, 32, 25, 35, 8, 17, 7, 9, 4, 2, 34, 10, 3, 0, 52).sorted()

    @Test
    fun testPartOne() {
        assertThat(dayTen.partOne(), `is`(220))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayTen.partTwo(), `is`(19208L))
    }

    @Test
    fun testCountJoltDifferences() {
        injectExample(exampleOne)
        assertThat(dayTen.countJoltDifferences(1), `is`(7))
        assertThat(dayTen.countJoltDifferences(3), `is`(5))
        injectExample(exampleTwo)
        assertThat(dayTen.countJoltDifferences(1), `is`(22))
        assertThat(dayTen.countJoltDifferences(3), `is`(10))
    }

    @Test
    fun testCountAdapterArrangements() {
        injectExample(exampleOne)
        assertThat(dayTen.countAdapterArrangements(), `is`(8))
        assertThat(dayTen.countAdapterArrangementsFast(), `is`(8L))
        injectExample(exampleTwo)
        assertThat(dayTen.countAdapterArrangements(), `is`(19208))
        assertThat(dayTen.countAdapterArrangementsFast(), `is`(19208L))
    }

    private fun injectExample(example: List<Int>) {
        val field = dayTen::class.java.getDeclaredField("ratings")
        field.isAccessible = true
        field.set(dayTen, example)
    }
}
