package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsIterableContainingInOrder.contains
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsNull.notNullValue
import org.junit.Test

class Day6Test {

    private val daySix = Day6()

    @Test
    fun testPartOne() {
        assertThat(daySix.partOne(), `is`(11))
    }

    @Test
    fun testPartTwo() {
        assertThat(daySix.partTwo(), `is`(6))
    }

    @Test
    fun testGroupParsing() {
        val field = Day6::class.java.getDeclaredField("groups")
        field.isAccessible = true
        val groups: List<Day6.Group> = field.get(Day6()) as List<Day6.Group>
        assertThat(groups, notNullValue())
        assertThat(groups, hasSize(5))

        assertGroupHas(groups[0], 1, listOf('a', 'b', 'c'), listOf('a', 'b', 'c'))
        assertGroupHas(groups[1], 3, listOf('a', 'b', 'c'), listOf('a', 'b', 'c'))
        assertGroupHas(groups[2], 2, listOf('a', 'b', 'a', 'c'), listOf('a', 'b', 'c'))
        assertGroupHas(groups[3], 4, listOf('a', 'a', 'a', 'a'), listOf('a'))
        assertGroupHas(groups[4], 1, listOf('b'), listOf('b'))
    }

    private fun assertGroupHas(group: Day6.Group, size: Int, allAnswers: List<Char>, uniqueAnswers: List<Char>) {
        assertThat(group.size, `is`(size))
        assertThat(group.allAnswers(), contains(*allAnswers.toTypedArray()))
        assertThat(group.uniqueAnswers(), contains(*uniqueAnswers.toTypedArray()))
    }
}
