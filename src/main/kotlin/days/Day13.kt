package days

class Day13 : Day(13) {

    override fun partOne(): Any {
        val timestamp = inputList.first().toInt()
        val buses = inputList.last().split(",").mapNotNull { it.toIntOrNull() }.map { Bus(it) }

        val bus = generateSequence(buses) { bs -> bs.map { it.loop() } }
            .find { bs -> bs.any { it.time >= timestamp } }
            ?.sortedBy { it.time }
            ?.find { it.time >= timestamp }

        return bus?.let { (it.time - timestamp) * it.id } ?: 0
    }

    override fun partTwo(): Any {
        return offsetDepartures(inputList.last().split(","))
    }

    // todo make more functional!
    fun offsetDepartures(rawBuses: List<String>): Long {
        val (buses, offsets) = rawBuses.foldIndexed(listOf<Int>() to listOf<Int>()) { index, lists, raw ->
            if (raw == "x") lists else lists.first.plus(raw.toInt()) to lists.second.plus(index)
        }

        var inc = buses.first().toLong()
        var timestamp = 0L
        var matched = 1
        while (matched < buses.size) {
            timestamp += inc
            val times = List(offsets.size) { it to timestamp + offsets[it] }
            if (times.take(matched + 1).all { (index, time) -> time % buses[index] == 0L }) {
                inc *= buses[matched++]
            }
        }
        return timestamp
    }

    data class Bus(val id: Int, val time: Int = 0) {

        fun loop() = Bus(id, time + id)
    }

}
