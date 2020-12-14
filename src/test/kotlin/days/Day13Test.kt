package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day13Test {

    private val dayThirteen = Day13()

    @Test
    fun testPartOne() {
        assertThat(dayThirteen.partOne(), `is`(295))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayThirteen.partTwo(), `is`(1068781L))
    }

    @Test
    fun testPartTwoExamples() {
        assertThat(dayThirteen.offsetDepartures(listOf("7" ,"13", "x", "x", "59", "x", "31", "19")), `is`(1068781))
        assertThat(dayThirteen.offsetDepartures(listOf("17", "x", "13", "19")), `is`(3417))
        assertThat(dayThirteen.offsetDepartures(listOf("67", "7", "59", "61")), `is`(754018))
        assertThat(dayThirteen.offsetDepartures(listOf("67", "x", "7", "59", "61")), `is`(779210))
        assertThat(dayThirteen.offsetDepartures(listOf("67", "7", "x", "59", "61")), `is`(1261476))
        assertThat(dayThirteen.offsetDepartures(listOf("1789", "37", "47", "1889")), `is`(1202161486))
    }
}
