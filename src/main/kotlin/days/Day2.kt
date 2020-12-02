package days

class Day2 : Day(2) {

    private val passwordsAndPolicies = inputList.map { PasswordAndPolicy(it) }

    // 542
    override fun partOne(): Any {
        return passwordsAndPolicies
            .filter { it.isValidPartOne() }
            .count()
    }

    // 360
    override fun partTwo(): Any {
        return passwordsAndPolicies
            .filter { it.isValidPartTwo() }
            .count()
    }

    private class PasswordAndPolicy(str: String) {
        private val password: String
        private val policyLetter: Char
        private val policyRange: IntRange

        init {
            val groups = REGEX.matchEntire(str)?.groupValues ?: emptyList()
            val rangeNums = groups[1].split("-").map { it.toInt() }
            policyRange = IntRange(rangeNums[0], rangeNums[1])
            policyLetter = groups[2].first()
            password = groups[3]
        }

        fun isValidPartOne(): Boolean {
            return policyRange.contains(password.count { it == policyLetter })
        }

        fun isValidPartTwo(): Boolean {
            val (pos1, pos2) = password[policyRange.first - 1] to password[policyRange.last - 1]
            return (pos1 == policyLetter) xor (pos2 == policyLetter)
        }

        override fun toString(): String {
            return "PasswordAndPolicy(password='$password', policyLetter=$policyLetter, policyRange=$policyRange)"
        }

        companion object {
            val REGEX = Regex("(\\d+-\\d+) ([a-z]): ([a-z]+)")
        }
    }
}
