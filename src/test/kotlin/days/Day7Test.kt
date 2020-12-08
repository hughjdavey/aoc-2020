package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day7Test {

    private val daySeven = Day7()

    @Test
    fun testPartOne() {
        assertThat(daySeven.partOne(), `is`(4))
    }

    @Test
    fun testPartTwo() {
        assertThat(daySeven.partTwo(), `is`(32))
    }

    @Test
    fun testPartTwoAnotherExample() {
        val example = listOf(
            "shiny gold bags contain 2 dark red bags.",
            "dark red bags contain 2 dark orange bags.",
            "dark orange bags contain 2 dark yellow bags.",
            "dark yellow bags contain 2 dark green bags.",
            "dark green bags contain 2 dark blue bags.",
            "dark blue bags contain 2 dark violet bags.",
            "dark violet bags contain no other bags.",
        )
        val bags = Day7.Bag.fromStrings(example)
        assertThat(Day7.Bag.mustContain(bags.find { it.colour == "shiny gold" }!!, bags), `is`(126))
    }
}
