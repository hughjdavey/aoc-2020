package days

import days.Day8.Operation.ACC
import days.Day8.Operation.JMP
import days.Day8.Operation.NOP

class Day8 : Day(8) {

    // 1928
    override fun partOne(): Any {
        return Program(inputList).run().accumulator
    }

    // 1319
    override fun partTwo(): Any {
        return sequence { yieldAll(inputList.mapIndexedNotNull { i, s -> if (s.startsWith("nop") || s.startsWith("jmp")) i else null }) }
            .map { Program(inputList).flipAt(it) }
            .map { it.run() }.first { it.isHalted }.accumulator
    }

    class Program(code: List<String>) {

        private val instructions = code.map { it.split(" ") }.map { (op, arg) -> Instruction(Operation.valueOf(op.toUpperCase()), arg.toInt()) }.toMutableList()

        private var isLooping = false
        private var isHalted = false
        private var accumulator = 0
        private var pointer = 0

        fun run(): ProgramState {
            while (refreshState()) {
                advance()
            }
            return ProgramState(accumulator, isLooping, isHalted)
        }

        fun flipAt(index: Int): Program {
            instructions[index] = instructions[index].flip()
            return this
        }

        private fun advance() {
            val instruction = instructions[pointer]
            if (instruction.operation == ACC) {
                accumulator += instruction.argument
            }
            pointer += if (instruction.operation == JMP) instruction.argument else 1
            instruction.executed = true
        }

        private fun refreshState(): Boolean {
            if (pointer > instructions.lastIndex) {
                isHalted = true
            }
            else if (instructions[pointer].executed) {
                isLooping = true
            }
            return !isLooping && !isHalted
        }
    }

    data class ProgramState(val accumulator: Int, val isLooping: Boolean, val isHalted: Boolean)

    data class Instruction(var operation: Operation, val argument: Int) {
        var executed = false

        fun flip(): Instruction {
            return when (operation) {
                JMP -> copy(operation = NOP)
                NOP -> copy(operation = JMP)
                ACC -> this
            }
        }
    }

    enum class Operation { ACC, JMP, NOP }
}
