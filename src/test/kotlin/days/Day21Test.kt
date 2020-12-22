package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day21Test {

    private val dayTwentyOne = Day21()
    private val input = listOf(
        "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)",
        "trh fvjkl sbzzf mxmxvkd (contains dairy)",
        "sqjhc fvjkl (contains soy)",
        "sqjhc mxmxvkd sbzzf (contains fish)",
    )

    @Test
    fun testPartOne() {
        assertThat(dayTwentyOne.partOne(), `is`(5))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayTwentyOne.partTwo(), `is`("mxmxvkd,sqjhc,fvjkl"))
    }

    @Test
    fun testParsingFoods() {
        val foods = dayTwentyOne.parseFoods(input)
        assertThat(foods, containsInAnyOrder(
            Day21.Food(listOf("mxmxvkd", "kfcds", "sqjhc", "nhms"), listOf("dairy", "fish")),
            Day21.Food(listOf("trh", "fvjkl", "sbzzf", "mxmxvkd"), listOf("dairy")),
            Day21.Food(listOf("sqjhc", "fvjkl"), listOf("soy")),
            Day21.Food(listOf("sqjhc", "mxmxvkd", "sbzzf"), listOf("fish")),
        ))
    }

    @Test
    fun testUnsafeIngredients() {
        val unsafeIngredients = dayTwentyOne.findUnsafeIngredients(dayTwentyOne.parseFoods(input))
        assertThat(unsafeIngredients.map { it.key to it.value }, containsInAnyOrder(
            "dairy" to "mxmxvkd",
            "fish" to "sqjhc",
            "soy" to "fvjkl",
        ))
    }
}
