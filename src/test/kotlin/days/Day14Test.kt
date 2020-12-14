package days

import days.Day14.MaskedInstructionV2.Companion.allBinariesOfLength
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsInAnyOrder
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day14Test {

    private val dayFourteen = Day14()

    @Test
    fun testPartOne() {
        assertThat(dayFourteen.partOne(), `is`(165L))
    }

    @Test
    fun testPartTwo() {
        // uses a different test input from part 1 so have to hack it in
        val instructions = dayFourteen.getInstructions(false, listOf(
            "mask = 000000000000000000000000000000X1001X",
            "mem[42] = 100",
            "mask = 00000000000000000000000000000000X0XX",
            "mem[26] = 1",
        ))
        assertThat(Day14.InstructionRunner().run(instructions), `is`(208L))
    }

    @Test
    fun testMaskingValue() {
        val mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"
        assertThat(Day14.MaskedInstructionV1(mask, 11, -1).maskedValue, `is`(73))
        assertThat(Day14.MaskedInstructionV1(mask, 101, -1).maskedValue, `is`(101))
        assertThat(Day14.MaskedInstructionV1(mask, 0, -1).maskedValue, `is`(64))
    }

    @Test
    fun testMaskingMemoryAddress() {
        assertThat(Day14.MaskedInstructionV2("000000000000000000000000000000X1001X", -1, 42).memoryAddresses, containsInAnyOrder(26, 27, 58, 59))
        assertThat(Day14.MaskedInstructionV2("00000000000000000000000000000000X0XX", -1, 26).memoryAddresses, containsInAnyOrder(16, 17, 18, 19, 24, 25, 26, 27))
    }

    @Test
    fun testAllBinariesOfLength() {
        assertThat(allBinariesOfLength(2), containsInAnyOrder("00", "01", "10", "11"))
        assertThat(allBinariesOfLength(3), containsInAnyOrder("000", "001", "010", "011", "100", "101", "110", "111"))
    }
}
