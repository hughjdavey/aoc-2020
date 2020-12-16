package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day16Test {

    private val daySixteen = Day16()

    @Test
    fun testPartOne() {
        assertThat(daySixteen.partOne(), `is`(71))
    }

    @Test
    fun testPartTwo() {
        // uses a different test input from part 1 so have to hack it in
        val input = listOf(
            "class: 0-1 or 4-19", "row: 0-5 or 8-19", "seat: 0-13 or 16-19",
            "\n",
            "your ticket:", "11,12,13",
            "\n",
            "nearby tickets:", "3,9,18", "15,1,5", "5,14,9",
        )
        Day16.customInput = input
        assertThat(Day16().getSolvedTicket(), containsInAnyOrder("row" to 11, "class" to 12, "seat" to 13))
        Day16.customInput = null
    }
}
