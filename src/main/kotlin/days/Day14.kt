package days

class Day14 : Day(14) {

    // 11327140210986
    override fun partOne(): Any {
        return InstructionRunner().run(getInstructions(true))
    }

    // 2308180581795
    override fun partTwo(): Any {
        return InstructionRunner().run(getInstructions(false))
    }

    fun getInstructions(v1: Boolean, input: List<String> = inputList): List<MaskedInstruction> {
        return input.fold("" to listOf<MaskedInstruction>()) { (mask, ins), line ->
            if (line.startsWith("mask")) line.replace("mask = ", "") to ins else {
                val assignment = Regex("mem\\[(\\d+)] = (\\d+)").matchEntire(line)!!.groupValues
                val (addr, value) = assignment[1] to assignment[2]
                mask to ins.plus(getInstruction(mask, value.toLong(), addr.toLong(), v1))
            }
        }.second
    }

    private fun getInstruction(mask: String, value: Long, addr: Long, v1: Boolean): MaskedInstruction {
        return if (v1) MaskedInstructionV1(mask, value, addr) else MaskedInstructionV2(mask, value, addr)
    }

    interface MaskedInstruction {
        val mask: String
        val unmaskedValue: Long
        val unmaskedMemoryAddress: Long
        val memoryAddresses: List<Long>
        val maskedValue: Long
    }

    data class MaskedInstructionV1(override val mask: String, override val unmaskedValue: Long, override val unmaskedMemoryAddress: Long) : MaskedInstruction {

        override val memoryAddresses = listOf(unmaskedMemoryAddress)

        override val maskedValue: Long by lazy {
            val unmaskedBin = unmaskedValue.toString(2).padStart(mask.length, '0')
            unmaskedBin.mapIndexed { index, c -> if (mask[index] == 'X') c else mask[index] }.joinToString("").toLong(2)
        }
    }

    data class MaskedInstructionV2(override val mask: String, override val unmaskedValue: Long, override val unmaskedMemoryAddress: Long) : MaskedInstruction {

        override val maskedValue = unmaskedValue

        override val memoryAddresses: List<Long> by lazy {
            val unmaskedBin = unmaskedMemoryAddress.toString(2).padStart(mask.length, '0')
            val result = unmaskedBin.mapIndexed { index, c -> if (mask[index] == '0') c else mask[index] }.joinToString("")

            allBinariesOfLength(result.count { it == 'X' })
                .map { result.replace("X", "%s").format(*it.toList().toTypedArray()) }
                .map { it.toLong(2) }
        }

        companion object {

            // e.g. if length = 2, this returns ["00", "01", "10", "11"]
            fun allBinariesOfLength(length: Int): List<String> {
                val high = "1".repeat(length).toLong(2)
                return (high downTo 0).map { it.toString(2).padStart(length, '0') }
            }
        }
    }

    class InstructionRunner {

        // had to use a map as the indices in part 2 exceed Integer.MAX_VALUE
        private val memory = mutableMapOf<Long, Long>()

        fun run(instructions: List<MaskedInstruction>): Long {
            instructions.forEach { instruction ->
                instruction.memoryAddresses.forEach { addr -> memory[addr] = instruction.maskedValue }
            }
            return memory.values.sum()
        }
    }
}
