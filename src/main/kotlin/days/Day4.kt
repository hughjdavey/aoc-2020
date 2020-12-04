package days

import isIntAndInRange

class Day4 : Day(4) {

    private val passports = inputString.split(Regex("\\W\\n", RegexOption.MULTILINE)).map { Passport(it) }

    // 213
    override fun partOne(): Any {
        return passports.count { it.hasValidKeys() }
    }

    // 147
    override fun partTwo(): Any {
        return passports.count { it.hasValidKeysAndValues() }
    }

    class Passport(str: String) {
        private val passport = Regex("([\\w]+:[\\w\\d#]+)").findAll(str).toList()
            .map { it.value }
            .map { val x = it.split(":"); x[0] to x[1] }.toMap()

        fun hasValidKeys(): Boolean {
            return passport.keys.containsAll(FIELDS)
        }

        // the !! assertions are safe below as we know the keys exist from the first predicate
        fun hasValidKeysAndValues(): Boolean {
            return hasValidKeys() && validBirthYear() && validIssueYear() && validExpiryYear() &&
                    validHeight() && validHairColour() && validEyeColour() && validPassportId()
        }

        fun validBirthYear() = passport["byr"]!!.isIntAndInRange(1920, 2002)

        fun validIssueYear() = passport["iyr"]!!.isIntAndInRange(2010, 2020)

        fun validExpiryYear() = passport["eyr"]!!.isIntAndInRange(2020, 2030)

        fun validHairColour() = passport["hcl"]!!.matches(Regex("#[a-f|0-9]{6}"))

        fun validEyeColour() = passport["ecl"]!!.matches(Regex("amb|blu|brn|gry|grn|hzl|oth"))

        fun validPassportId() = passport["pid"]!!.matches(Regex("[0-9]{9}"))

        fun validHeight(): Boolean {
            val r = Regex("^(\\d+)(cm|in)\$").matchEntire(passport["hgt"]!!) ?: return false
            val (n, unit) = r.groupValues[1] to r.groupValues[2]
            return (unit == "cm" || unit == "in") && if (unit == "cm") n.isIntAndInRange(150, 193) else n.isIntAndInRange(59, 76)
        }

        companion object {
            val FIELDS = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
        }
    }
}
