package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsIterableContainingInOrder.contains
import org.hamcrest.core.Is.`is`
import org.junit.Test
import java.io.File

class Day9Test {

    private val dayNine = Day9()
    private val numbers = File(javaClass.classLoader.getResource("input_day_9.txt").toURI()).readLines().map { it.toLong() }

    // can't directly call partOne and partTwo in below tests as the example and real tasks use different preamble sizes (5 vs 25)

    @Test
    fun testPartOne() {
        assertThat(dayNine.findFirstInvalidNumber(numbers, 5), `is`(127))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayNine.findSetThatSumsTo(numbers, 127), contains(15, 25, 47, 40))
        assertThat(dayNine.findEncryptionWeakness(numbers, 127), `is`(62))
    }
}
