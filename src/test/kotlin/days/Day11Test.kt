package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsEmptyCollection.empty
import org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder
import org.hamcrest.collection.IsIterableContainingInOrder.contains
import org.hamcrest.core.Is.`is`
import org.junit.Test
import java.io.File

class Day11Test {

    private val dayEleven = Day11()
    private val inputList = File(javaClass.classLoader.getResource("input_day_11.txt").toURI()).readLines()

    @Test
    fun testPartOne() {
        assertThat(dayEleven.partOne(), `is`(37))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayEleven.partTwo(), `is`(26))
    }

    @Test
    fun testSeatLayout() {
        val seatLayout = Day11.SeatLayout(inputList)
        assertThat(seatLayout.get(0, 0), `is`('L'))
        assertThat(seatLayout.get(1, 1), `is`('L'))
        assertThat(seatLayout.get(2, 2), `is`('L'))
        assertThat(seatLayout.get(7, 3), `is`('.'))
        assertThat(seatLayout.get(100, 1), `is`(' '))
    }

    @Test
    fun testAdjacent() {
        val seatLayout = Day11.SeatLayout(inputList)
        assertThat(seatLayout.getAdjacent(0, 0), containsInAnyOrder(
            '.', 'L', 'L'
        ))
        assertThat(seatLayout.getAdjacent(7, 3), containsInAnyOrder(
            'L', '.', 'L', '.', 'L', 'L', '.', 'L'
        ))
    }

    @Test
    fun testAdjacentSeats() {
        val example1 = Day11.SeatLayout(listOf(
            ".......#.",
            "...#.....",
            ".#.......",
            ".........",
            "..#L....#",
            "....#....",
            ".........",
            "#........",
            "...#.....",
        ))
        assertThat(example1.get(3, 4), `is`('L'))
        assertThat(example1.getAdjacentSeats(3, 4), contains('#', '#', '#', '#', '#', '#', '#', '#'))

        val example2 = Day11.SeatLayout(listOf(
            ".............",
            ".L.L.#.#.#.#.",
            ".............",
        ))
        assertThat(example2.get(1, 1), `is`('L'))
        assertThat(example2.getAdjacentSeats(1, 1), contains('L'))

        val example3 = Day11.SeatLayout(listOf(
            ".##.##.",
            "#.#.#.#",
            "##...##",
            "...L...",
            "##...##",
            "#.#.#.#",
            ".##.##.",
        ))
        assertThat(example3.get(3, 3), `is`('L'))
        assertThat(example3.getAdjacentSeats(3, 3), empty())
    }

    @Test
    fun testApplyRules() {
        val seatLayout = Day11.SeatLayout(inputList)
        val round1 = seatLayout.applyRules()
        assertThat(round1.toString(), `is`(
            "#.##.##.##\n" +
            "#######.##\n" +
            "#.#.#..#..\n" +
            "####.##.##\n" +
            "#.##.##.##\n" +
            "#.#####.##\n" +
            "..#.#.....\n" +
            "##########\n" +
            "#.######.#\n" +
            "#.#####.##"
        ))

        val round2 = round1.applyRules()
        assertThat(round2.toString(), `is`(
            "#.LL.L#.##\n" +
            "#LLLLLL.L#\n" +
            "L.L.L..L..\n" +
            "#LLL.LL.L#\n" +
            "#.LL.LL.LL\n" +
            "#.LLLL#.##\n" +
            "..L.L.....\n" +
            "#LLLLLLLL#\n" +
            "#.LLLLLL.L\n" +
            "#.#LLLL.##"
        ))

        val round3 = round2.applyRules()
        assertThat(round3.toString(), `is`(
            "#.##.L#.##\n" +
            "#L###LL.L#\n" +
            "L.#.#..#..\n" +
            "#L##.##.L#\n" +
            "#.##.LL.LL\n" +
            "#.###L#.##\n" +
            "..#.#.....\n" +
            "#L######L#\n" +
            "#.LL###L.L\n" +
            "#.#L###.##"
        ))

        val round4 = round3.applyRules()
        assertThat(round4.toString(), `is`(
            "#.#L.L#.##\n" +
            "#LLL#LL.L#\n" +
            "L.L.L..#..\n" +
            "#LLL.##.L#\n" +
            "#.LL.LL.LL\n" +
            "#.LL#L#.##\n" +
            "..L.L.....\n" +
            "#L#LLLL#L#\n" +
            "#.LLLLLL.L\n" +
            "#.#L#L#.##"
        ))

        val round5 = round4.applyRules()
        assertThat(round5.toString(), `is`(
            "#.#L.L#.##\n" +
            "#LLL#LL.L#\n" +
            "L.#.L..#..\n" +
            "#L##.##.L#\n" +
            "#.#L.LL.LL\n" +
            "#.#L#L#.##\n" +
            "..L.L.....\n" +
            "#L#L##L#L#\n" +
            "#.LLLLLL.L\n" +
            "#.#L#L#.##"
        ))
    }

    @Test
    fun testApplyCorrectedRules() {
        val seatLayout = Day11.SeatLayout(inputList)
        val round1 = seatLayout.applyCorrectedRules()
        assertThat(round1.toString(), `is`(
            "#.##.##.##\n" +
            "#######.##\n" +
            "#.#.#..#..\n" +
            "####.##.##\n" +
            "#.##.##.##\n" +
            "#.#####.##\n" +
            "..#.#.....\n" +
            "##########\n" +
            "#.######.#\n" +
            "#.#####.##"
        ))

        val round2 = round1.applyCorrectedRules()
        assertThat(round2.toString(), `is`(
            "#.LL.LL.L#\n" +
            "#LLLLLL.LL\n" +
            "L.L.L..L..\n" +
            "LLLL.LL.LL\n" +
            "L.LL.LL.LL\n" +
            "L.LLLLL.LL\n" +
            "..L.L.....\n" +
            "LLLLLLLLL#\n" +
            "#.LLLLLL.L\n" +
            "#.LLLLL.L#"
        ))

        val round3 = round2.applyCorrectedRules()
        assertThat(round3.toString(), `is`(
            "#.L#.##.L#\n" +
            "#L#####.LL\n" +
            "L.#.#..#..\n" +
            "##L#.##.##\n" +
            "#.##.#L.##\n" +
            "#.#####.#L\n" +
            "..#.#.....\n" +
            "LLL####LL#\n" +
            "#.L#####.L\n" +
            "#.L####.L#"
        ))

        val round4 = round3.applyCorrectedRules()
        assertThat(round4.toString(), `is`(
            "#.L#.L#.L#\n" +
            "#LLLLLL.LL\n" +
            "L.L.L..#..\n" +
            "##LL.LL.L#\n" +
            "L.LL.LL.L#\n" +
            "#.LLLLL.LL\n" +
            "..L.L.....\n" +
            "LLLLLLLLL#\n" +
            "#.LLLLL#.L\n" +
            "#.L#LL#.L#"
        ))

        val round5 = round4.applyCorrectedRules()
        assertThat(round5.toString(), `is`(
            "#.L#.L#.L#\n" +
             "#LLLLLL.LL\n" +
             "L.L.L..#..\n" +
             "##L#.#L.L#\n" +
             "L.L#.#L.L#\n" +
             "#.L####.LL\n" +
             "..#.#.....\n" +
             "LLL###LLL#\n" +
             "#.LLLLL#.L\n" +
             "#.L#LL#.L#"
        ))

        val round6 = round5.applyCorrectedRules()
        assertThat(round6.toString(), `is`(
            "#.L#.L#.L#\n" +
            "#LLLLLL.LL\n" +
            "L.L.L..#..\n" +
            "##L#.#L.L#\n" +
            "L.L#.LL.L#\n" +
            "#.LLLL#.LL\n" +
            "..#.L.....\n" +
            "LLL###LLL#\n" +
            "#.LLLLL#.L\n" +
            "#.L#LL#.L#"
        ))
    }
}
