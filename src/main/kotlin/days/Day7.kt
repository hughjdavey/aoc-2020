package days

class Day7 : Day(7) {

    private val bags = Bag.fromStrings(inputList)

    // 289
    override fun partOne(): Any {
        return Bag.canContain("shiny gold", bags)
    }

    // 30055
    override fun partTwo(): Any {
        return Bag.mustContain(bags.find { it.colour == "shiny gold" }!!, bags)
    }

    data class Bag(val colour: String, val children: List<String> = mutableListOf()) {

        companion object {

            // todo rewrite in more functional style!
            fun canContain(colour: String, bags: List<Bag>): Int {
                var oldLen: Int
                val colours = mutableSetOf(colour)
                do {
                    oldLen = colours.size
                    colours.addAll(bags.filter { it.children.any { colours.contains(it) } }.map { it.colour })
                } while (oldLen != colours.size)
                return colours.minus(colour).size
            }

            fun mustContain(bag: Bag, bags: List<Bag>, count: Int = 0): Int {
                return if (bag.children.isEmpty()) count else {
                    bag.children.count() + bag.children.map { mustContain(bags.find { b -> b.colour == it }!!, bags, count) }.sum()
                }
            }

            fun fromStrings(strings: List<String>): List<Bag> {
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
