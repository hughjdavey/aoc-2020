package days

class Day7 : Day(7) {

    private val bags = Bag.createBags(inputList)
    private val bagColour = "shiny gold"

    // 289
    override fun partOne(): Any {
        return bags.count { Bag.canContain(it, bagColour) }
    }

    // 30055
    override fun partTwo(): Any {
        return Bag.mustContain(Bag.fromColour(bagColour))
    }

    data class Bag(val colour: String, val children: List<String> = mutableListOf()) {

        companion object {

            private lateinit var bags: List<Bag>

            fun canContain(bag: Bag, colour: String): Boolean {
                return if (bag.children.isEmpty()) false else {
                    bag.children.contains(colour) || fromColours(bag.children).any { canContain(it, colour) }
                }
            }

            fun mustContain(bag: Bag, count: Int = 0): Int {
                return if (bag.children.isEmpty()) count else {
                    bag.children.count() + bag.children.map { mustContain(fromColour(it), count) }.sum()
                }
            }

            fun createBags(strings: List<String>): List<Bag> {
                bags = fromStrings(strings)
                return bags
            }

            fun fromColour(colour: String): Bag {
                return bags.find { b -> b.colour == colour }!!
            }

            private fun fromColours(colours: List<String>): List<Bag> {
                return bags.filter { colours.contains(it.colour) }
            }

            private fun fromStrings(strings: List<String>): List<Bag> {
                return strings.map { it.replace(Regex("bags?|[.]"), "").split("contain").map { it.trim() } }
                    .fold(listOf()) { bags, str ->
                        val (colour, children) = str[0] to str[1].split(" , ").mapNotNull { if (it == "no other") null else {
                            val split = Regex("(\\d+)\\s(.*)").matchEntire(it)!!.groupValues.drop(1)
                            split.last() to split.first().toInt()
                        } }.map { (col, count) -> List(count) { col } }.flatten()
                        bags.plus(Bag(colour, children))
                    }
            }
        }
    }
}
