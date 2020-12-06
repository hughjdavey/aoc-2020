package days

class Day6 : Day(6) {

    private val groups = inputString.split(Regex("\\W\\n", RegexOption.MULTILINE)).map { Group(it) }

    // 7110
    override fun partOne(): Any {
        return groups.sumBy { it.uniqueAnswers().count() }
    }

    // 3628
    override fun partTwo(): Any {
        return groups.map { g -> g.uniqueAnswers().count { u -> g.allAnswers().count{ it == u } == g.size } }.sum()
    }

    class Group(str: String, private val answers: List<String> = str.split("\n").filterNot { it.isBlank() }) {

        val size = answers.size

        fun allAnswers() = answers.joinToString("").toList()

        fun uniqueAnswers() = answers.joinToString("").toSet()
    }
}
