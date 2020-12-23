package days

class Day21 : Day(21) {

    private val foods = parseFoods()
    private val unsafeIngredients = findUnsafeIngredients(foods)

    // 2125
    override fun partOne(): Any {
        return foods.map { it.ingredients }.flatten().count { it !in unsafeIngredients.values }
    }

    // phc,spnd,zmsdzh,pdt,fqqcnm,lsgqf,rjc,lzvh
    override fun partTwo(): Any {
        return unsafeIngredients.entries.sortedBy { it.key }.joinToString(",") { it.value }
    }

    fun parseFoods(input: List<String> = inputList): List<Food> {
        return input.map {
            val (ingredients, allergens) = it.split("(contains").map { it.trim() }
            Food(ingredients.split(" "), allergens.dropLast(1).split(", "))
        }
    }

    fun findUnsafeIngredients(foods: List<Food>): Map<String, String> {
        val allergensToIngredients = foods.map { it.allergens }.flatten().toSet().map { it to "" }.toMap().toMutableMap()

        val commons = allergensToIngredients.keys.map { allergen ->
            val containing = foods.filter { it.allergens.contains(allergen) }
            val common = containing.map { it.ingredients }.fold(listOf<String>()) { acc, ingreds ->
                if (acc.isEmpty()) acc.plus(ingreds) else ingreds.filter { acc.contains(it) }
            }
            allergen to common.toMutableList()
        }

        while (allergensToIngredients.values.any { it == "" }) {
            commons.forEach { c ->
                if (c.second.size == 1) {
                    val ingredient = c.second.first()
                    allergensToIngredients[c.first] = ingredient
                    commons.filterNot { it.second.isEmpty() }.forEach { it.second.removeIf { it == ingredient } }
                }
            }
        }
        return allergensToIngredients
    }

    data class Food(val ingredients: List<String>, val allergens: List<String>)
}
