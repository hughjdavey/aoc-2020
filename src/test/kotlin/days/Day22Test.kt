package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsEmptyCollection.empty
import org.hamcrest.collection.IsIterableContainingInOrder.contains
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day22Test {

    private val dayTwentyTwo = Day22()

    @Test
    fun testPartOne() {
        assertThat(dayTwentyTwo.partOne(), `is`(306))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayTwentyTwo.partTwo(), `is`(291))
    }

    @Test
    fun testPlayUntilOver() {
        val game = Day22.Combat(ArrayDeque(listOf(9, 2, 6, 3, 1)), ArrayDeque(listOf(5, 8, 4, 7, 10)))
        val state = game.playUntilOver()
        assertThat(game.player1, empty())
        assertThat(game.player2, contains(3, 2, 10, 6, 8, 5, 9, 4, 7, 1))
        assertThat(state.rounds, `is`(29))
        assertThat(state.winner, `is`(2))
        assertThat(state.deck, `is`(game.player2))
        assertThat(state.score, `is`(306))
    }
}
