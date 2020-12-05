package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day5Test {

    private val dayFive = Day5()

    @Test
    fun testPartOne() {
        assertThat(dayFive.partOne(), `is`(820))
    }

    // can't do a part 2 test with the provided test data set

    @Test
    fun testSeatParsing() {
        assertSeatHas(Day5.Seat("FBFBBFFRLR"), 5, 44, 357)
        assertSeatHas(Day5.Seat("BFFFBBFRRR"), 7, 70, 567)
        assertSeatHas(Day5.Seat("FFFBBBFRRR"), 7, 14, 119)
        assertSeatHas(Day5.Seat("BBFFBBFRLL"), 4, 102, 820)
    }

    private fun assertSeatHas(seat: Day5.Seat, col: Int, row: Int, uniqueId: Int) {
        assertThat(seat.col, `is`(col))
        assertThat(seat.row, `is`(row))
        assertThat(seat.uniqueId, `is`(uniqueId))
    }
}
