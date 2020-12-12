package days

import Coord
import Direction

class Day12 : Day(12) {

    private val instructions = inputList.map { Instruction(it.first(), it.drop(1).toInt()) }

    // 1007
    override fun partOne(): Any {
        return Ship(false).followInstructions(instructions).position.manhattan()
    }

    // 41212
    override fun partTwo(): Any {
        return Ship(true).followInstructions(instructions).position.manhattan()
    }

    class Ship(private val usesWaypoint: Boolean) {

        private var direction = Direction.EAST
        private var waypoint = Coord(10, 1)

        var position = Coord(0, 0)

        fun followInstructions(instructions: List<Instruction>): Ship {
            return instructions.fold(this) { ship, ins -> ship.respond(ins) }
        }

        fun respond(instruction: Instruction): Ship {
            when (instruction.action) {
                'N', 'E', 'S', 'W' -> setPos(instruction)
                'L', 'R' -> setDirection(instruction)
                'F' -> move(instruction.value)
                else -> throw IllegalArgumentException()
            }
            return this
        }

        private fun setPos(instruction: Instruction) {
            val func = when (instruction.action) {
                'N' -> { c: Coord -> c.plusY(instruction.value) }
                'E' -> { c: Coord -> c.plusX(instruction.value) }
                'S' -> { c: Coord -> c.minusY(instruction.value) }
                'W' -> { c: Coord -> c.minusX(instruction.value) }
                else -> throw IllegalArgumentException()
            }

            if (usesWaypoint) waypoint = func(waypoint) else position = func(position)
        }

        private fun setDirection(instruction: Instruction) {
            if (usesWaypoint) {
                waypoint = if (instruction.action == 'L') waypoint.rotate(position, -instruction.value) else waypoint.rotate(position, instruction.value)
            }
            else {
                direction = if (instruction.action == 'L') direction.rotate(-instruction.value) else direction.rotate(instruction.value)
            }
        }

        private fun move(units: Int) {
            if (usesWaypoint) {
                val diff = waypoint.diff(position)
                position = position.plusX(diff.x * units).plusY(diff.y * units)
                waypoint = Coord(position.x + diff.x, position.y + diff.y)
            }
            else {
                setPos(Instruction(direction.name.first(), units))
            }
        }
    }

    data class Instruction(val action: Char, val value: Int)
}
