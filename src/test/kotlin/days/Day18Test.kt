package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day18Test {

    private val dayEighteen = Day18()

    @Test
    fun testPartOne() {
        assertThat(dayEighteen.partOne(), `is`(26457L))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayEighteen.partTwo(), `is`(694173L))
    }

    @Test
    fun testExampleExpressionsForPartOne() {
        assertThat(dayEighteen.doCalculation("1 + 2 * 3 + 4 * 5 + 6"), `is`(71))
        assertThat(dayEighteen.doCalculation("1 + (2 * 3) + (4 * (5 + 6))"), `is`(51))
        assertThat(dayEighteen.doCalculation("2 * 3 + (4 * 5)"), `is`(26))
        assertThat(dayEighteen.doCalculation("5 + (8 * 3 + 9 + 3 * 4 * 3)"), `is`(437))
        assertThat(dayEighteen.doCalculation("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"), `is`(12240))
        assertThat(dayEighteen.doCalculation("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"), `is`(13632))
    }

    @Test
    fun testExampleExpressionsForPart2() {
        assertThat(dayEighteen.doCalculation("1 + 2 * 3 + 4 * 5 + 6", true), `is`(231))
        assertThat(dayEighteen.doCalculation("1 + (2 * 3) + (4 * (5 + 6))", true), `is`(51))
        assertThat(dayEighteen.doCalculation("2 * 3 + (4 * 5)", true), `is`(46))
        assertThat(dayEighteen.doCalculation("5 + (8 * 3 + 9 + 3 * 4 * 3)", true), `is`(1445))
        assertThat(dayEighteen.doCalculation("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))", true), `is`(669060))
        assertThat(dayEighteen.doCalculation("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", true), `is`(23340))
    }

    // when my part 2 worked with the examples but not for real i used this reddit solution
    // https://www.reddit.com/r/adventofcode/comments/kfeldk/2020_day_18_solutions/gg8t8qp/
    // to find a few examples from my real input where my answer differed from a correct one
    @Test
    fun testExtraExamples() {
        assertThat(dayEighteen.doCalculation("5 * 5 + 9 + (5 * 2 + 4 + 8 + 6 * 4) * 9 * 3", true), `is`(55890))
        assertThat(dayEighteen.doCalculation("9 + 9 * 2 * 8 + ((7 * 7 * 8 * 3) + 7 + 2 * 3) * 4", true), `is`(513072))
        assertThat(dayEighteen.doCalculation("4 * 8 + (2 * 5 * (3 * 7 + 3 * 6) * 9) * (2 + 4 * 6 * 2)", true), `is`(4667904))
    }
}
