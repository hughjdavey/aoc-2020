import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder
import org.hamcrest.core.Is.`is`
import org.junit.Test

class UtilsTest {

    @Test
    fun testAllPairsAndAllTriples() {
        val xs = listOf(1, 2, 3, 4)
        val expectedPairs = listOf(
                Pair(1, 1), Pair(1, 2), Pair(1, 3), Pair(1, 4),
                Pair(2, 1), Pair(2, 2), Pair(2, 3), Pair(2, 4),
                Pair(3, 1), Pair(3, 2), Pair(3, 3), Pair(3, 4),
                Pair(4, 1), Pair(4, 2), Pair(4, 3), Pair(4, 4),
        )
        assertThat(xs.allPairs(), containsInAnyOrder(*expectedPairs.toTypedArray()))

        // too long to write out all triples!
        val expectedTriples = listOf(1, 2, 3, 4).flatMap { n -> expectedPairs.map { p -> Triple(n, p.first, p.second) } }
        assertThat(xs.allTriples(), containsInAnyOrder(*expectedTriples.toTypedArray()))

    }

    @Test
    fun testPairAndTripleAddAndMul() {
        assertThat(Pair(4, 4).add(), `is`(8))
        assertThat(Pair(4, 4).mul(), `is`(16))
        assertThat(Triple(4, 4, 4).add(), `is`(12))
        assertThat(Triple(4, 4, 4).mul(), `is`(64))
    }

    @Test
    fun testIsIntAndInRange() {
        assertThat("".isIntAndInRange(5, 10), `is`(false))
        assertThat("11".isIntAndInRange(5, 10), `is`(false))
        assertThat("5cm".isIntAndInRange(5, 10), `is`(false))
        assertThat("5".isIntAndInRange(5, 10), `is`(true))
        assertThat("7".isIntAndInRange(5, 10), `is`(true))
        assertThat("10".isIntAndInRange(5, 10), `is`(true))
    }
}
