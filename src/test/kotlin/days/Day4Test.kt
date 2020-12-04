package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day4Test {

    private val day4 = Day4()

    @Test
    fun testPartOne() {
        assertThat(day4.partOne(), `is`(2))
    }

    @Test
    fun testPartTwo() {
        assertThat(day4.partTwo(), `is`(2))
    }

    @Test
    fun testExampleValuesForValidity() {
        assertThat(Day4.Passport("byr:2002").validBirthYear(), `is`(true))
        assertThat(Day4.Passport("byr:2003").validBirthYear(), `is`(false))

        assertThat(Day4.Passport("hgt:60in").validHeight(), `is`(true))
        assertThat(Day4.Passport("hgt:190cm").validHeight(), `is`(true))
        assertThat(Day4.Passport("hgt:190in").validHeight(), `is`(false))
        assertThat(Day4.Passport("hgt:190").validHeight(), `is`(false))

        assertThat(Day4.Passport("hcl:#123abc").validHairColour(), `is`(true))
        assertThat(Day4.Passport("hcl:#123abz").validHairColour(), `is`(false))
        assertThat(Day4.Passport("hcl:123abc").validHairColour(), `is`(false))

        assertThat(Day4.Passport("ecl:brn").validEyeColour(), `is`(true))
        assertThat(Day4.Passport("ecl:wat").validEyeColour(), `is`(false))

        assertThat(Day4.Passport("pid:000000001").validPassportId(), `is`(true))
        assertThat(Day4.Passport("pid:0123456789").validPassportId(), `is`(false))
    }

    @Test
    fun testExamplePassportsForValidity() {
        assertThat(Day4.Passport("eyr:1972 cid:100\nhcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926").hasValidKeysAndValues(), `is`(false))
        assertThat(Day4.Passport("iyr:2019\nhcl:#602927 eyr:1967 hgt:170cm\necl:grn pid:012533040 byr:1946").hasValidKeysAndValues(), `is`(false))
        assertThat(Day4.Passport("hcl:dab227 iyr:2012\necl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277").hasValidKeysAndValues(), `is`(false))
        assertThat(Day4.Passport("hgt:59cm ecl:zzz\neyr:2038 hcl:74454a iyr:2023\npid:3556412378 byr:2007").hasValidKeysAndValues(), `is`(false))

        assertThat(Day4.Passport("pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980\nhcl:#623a2f").hasValidKeysAndValues(), `is`(true))
        assertThat(Day4.Passport("eyr:2029 ecl:blu cid:129 byr:1989\niyr:2014 pid:896056539 hcl:#a97842 hgt:165cm").hasValidKeysAndValues(), `is`(true))
        assertThat(Day4.Passport("hcl:#888785\nhgt:164cm byr:2001 iyr:2015 cid:88\npid:545766238 ecl:hzl\neyr:2022").hasValidKeysAndValues(), `is`(true))
        assertThat(Day4.Passport("iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719").hasValidKeysAndValues(), `is`(true))
    }
}
