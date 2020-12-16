package days

import splitOnBlank

class Day16 : Day(16) {

    private val ticketFields: List<TicketField>
    private val yourTicket: List<Int>
    private val nearbyTickets: List<List<Int>>

    init {
        val (fields, your, nearby) = if (customInput != null) customInput!!.splitOnBlank() else inputList.splitOnBlank()
        ticketFields = fields
            .map { Regex("(\\w+ ?\\w+?): (\\d+-\\d+) or (\\d+-\\d+)").matchEntire(it)!!.groupValues }
            .map { (_, name, range1, range2) -> TicketField(name, dashRangeToList(range1).plus(dashRangeToList(range2))) }
        yourTicket = your.last().split(",").map { it.toInt() }
        nearbyTickets = nearby.drop(1).map { it.split(",").map { it.toInt() } }
    }

    private fun dashRangeToList(dashRange: String): List<Int> {
        val (lo, hi) = dashRange.split("-").map { it.toInt() }
        return (lo..hi).toList()
    }

    // 23925
    override fun partOne(): Any {
        return nearbyTickets
            .map { ticket -> ticket.map { it to ticketFields.notValidForAny(it) } }
            .flatten().filter { it.second }
            .sumBy { it.first }
    }

    // 964373157673
    override fun partTwo(): Any {
        return getSolvedTicket()
            .filter { it.first.startsWith("departure") }
            .fold(1L) { acc, elem -> acc * elem.second }
    }

    // todo make more functional!
    fun getSolvedTicket(): List<Pair<String, Int>> {
        val nearbyTickets = nearbyTickets.filterNot { ticket -> ticket.any { ticketFields.notValidForAny(it) } }
        val allRows = (0..nearbyTickets.first().lastIndex).map { idx -> nearbyTickets.map { it[idx] } }
        val potentialFields = allRows.map { r -> ticketFields.filter { it.valid.containsAll(r) }.map { it.name }.toMutableList() }.toMutableList()

        val realFields = Array(ticketFields.size) { "" }
        while (realFields.any { it == "" }) {
            potentialFields.forEachIndexed { index, potentials ->
                when {
                    potentials.isEmpty() -> return@forEachIndexed
                    potentials.size == 1 -> realFields[index] = potentials.removeAt(0)
                    else -> potentials.removeIf { realFields.contains(it) }
                }
            }
        }
        return realFields.zip(yourTicket)
    }

    data class TicketField(val name: String, val valid: List<Int>, var value: Int = -1)

    private fun List<TicketField>.notValidForAny(value: Int): Boolean {
        return this.none { it.valid.contains(value) }
    }

    companion object {

        // hack for testing
        var customInput: List<String>? = null
    }
}

