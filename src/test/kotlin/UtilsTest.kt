import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder
import org.hamcrest.collection.IsIterableContainingInOrder.contains
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

    @Test
    fun testDirectionRotate() {
        assertThat(Direction.NORTH.rotate(90), `is`(Direction.EAST))
        assertThat(Direction.NORTH.rotate(-90), `is`(Direction.WEST))
        assertThat(Direction.NORTH.rotate(180), `is`(Direction.SOUTH))
        assertThat(Direction.NORTH.rotate(-180), `is`(Direction.SOUTH))
        assertThat(Direction.NORTH.rotate(270), `is`(Direction.WEST))
        assertThat(Direction.NORTH.rotate(-270), `is`(Direction.EAST))
        assertThat(Direction.NORTH.rotate(360), `is`(Direction.NORTH))
        assertThat(Direction.NORTH.rotate(-360), `is`(Direction.NORTH))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testDirectionRotateInvalid() {
        Direction.NORTH.rotate(24)
    }

    @Test
    fun testCoordRotate() {
        val axis = Coord2(170, 38)
        assertThat(Coord2(180, 42).rotate(axis, 90), `is`(Coord2(174, 28)))
        assertThat(Coord2(180, 42).rotate(axis, 180), `is`(Coord2(160, 34)))
        assertThat(Coord2(180, 42).rotate(axis, 270), `is`(Coord2(166, 48)))
        assertThat(Coord2(180, 42).rotate(axis, 360), `is`(Coord2(180, 42)))

        val axis2 = Coord2(0, 0)
        assertThat(Coord2(0, 5).rotate(axis2, -90), `is`(Coord2(-5, 0)))
        assertThat(Coord2(0, 5).rotate(axis2, -180), `is`(Coord2(0, -5)))
        assertThat(Coord2(0, 5).rotate(axis2, -270), `is`(Coord2(5, 0)))
        assertThat(Coord2(0, 5).rotate(axis2, 180).rotate(axis2, 180), `is`(Coord2(0, 5)))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testCoordRotateInvalid() {
        Coord2(1, 2).rotate(Coord2(3, 4), 48)
    }

    @Test
    fun testCoordDiffAndManhattan() {
        assertThat(Coord2(4, 4).diff(), `is`(Coord2(4, 4)))
        assertThat(Coord2(-4, -4).diff(), `is`(Coord2(-4, -4)))
        assertThat(Coord2(-4, -4).diff(Coord2(-2, 2)), `is`(Coord2(-2, -6)))

        assertThat(Coord2(4, 4).manhattan(), `is`(8))
        assertThat(Coord2(-4, -4).manhattan(), `is`(8))
        assertThat(Coord2(-6, 2).manhattan(), `is`(8))
        assertThat(Coord2(-6, 2).manhattan(Coord2(16, -8)), `is`(32))
    }

    @Test
    fun testSplitOnBlank() {
        assertThat(listOf("1", "", "2", "", "3").splitOnBlank(), contains(listOf("1"), listOf("2"), listOf("3")))
        assertThat(listOf("1", "\n", "2", "\n", "3").splitOnBlank(), contains(listOf("1"), listOf("2"), listOf("3")))
        assertThat(listOf("1", "\n\n", "2", "\n\n", "3").splitOnBlank(), contains(listOf("1"), listOf("2"), listOf("3")))
        assertThat(listOf("names", "alice", "bob", "\n", "ages", "24", "36").splitOnBlank(), contains(listOf("names", "alice", "bob"), listOf("ages", "24", "36")))
    }

    @Test
    fun testStackOf() {
        val stackFromList = stackOf(listOf(1, 2, 3))
        assertThat(stackFromList, hasSize(3))
        assertThat(stackFromList.pop(), `is`(1))
        assertThat(stackFromList.pop(), `is`(2))
        assertThat(stackFromList.pop(), `is`(3))

        val stackFromVarargs = stackOf(9, 18, 27)
        assertThat(stackFromVarargs, hasSize(3))
        assertThat(stackFromVarargs.pop(), `is`(9))
        assertThat(stackFromVarargs.pop(), `is`(18))
        assertThat(stackFromVarargs.pop(), `is`(27))
    }

    @Test
    fun testCoordAdjacent() {
        assertThat(Coord2(0, 0).getAdjacent(), hasSize(8))
        assertThat(Coord3(0, 0, 0).getAdjacent(), hasSize(26))
        assertThat(Coord4(0, 0, 0, 0).getAdjacent(), hasSize(80))
    }
}
