package days

import splitOnBlank

class Day22 : Day(22) {

    // 34566
    override fun partOne(): Any {
        return getGame(false).playUntilOver().score
    }

    // 31854
    override fun partTwo(): Any {
        return getGame(true).playUntilOver().score
    }

    private fun getGame(recursive: Boolean): Combat {
        val (p1, p2) = inputList.splitOnBlank().map { ArrayDeque(it.drop(1).map { it.toInt() }) }
        return Combat(p1, p2, recursive)
    }

    // todo recursive iteration takes around 3 seconds - could be improvded
    data class Combat(val player1: ArrayDeque<Int>, val player2: ArrayDeque<Int>, val recursive: Boolean = false) {

        private val snapshots = mutableListOf<CombatSnapshot>()

        fun playUntilOver(): CombatState {
            var rounds = 0
            while (player1.isNotEmpty() && player2.isNotEmpty()) {
                rounds++
                // changed to || to && based on following reddit discussion (also && was not finishing)
                // https://www.reddit.com/r/adventofcode/comments/kicn13/2020_day_22_interpretation_of_previously_seen/
                if (recursive && snapshots.any { it.player1 == player1.toList() || it.player2 == player2.toList() }) {
                    player2.clear()
                    break
                }

                if (recursive) {
                    snapshots.add(CombatSnapshot(rounds, player1.toList(), player2.toList()))
                }
                round()
            }
            val winner = if (player1.isEmpty()) 2 to player2 else 1 to player1
            return CombatState(rounds, winner.first, winner.second, score(winner.second))
        }

        private fun round() {
            val (p1, p2) = player1.removeFirst() to player2.removeFirst()

            val p1Win = if (recursive && player1.count() >= p1 && player2.count() >= p2) {
                Combat(ArrayDeque(player1.toList().take(p1)), ArrayDeque(player2.toList().take(p2)), true).playUntilOver().winner == 1
            }
            else p1 > p2

            if (p1Win) player1.addAll(listOf(p1, p2)) else player2.addAll(listOf(p2, p1))
        }

        private fun score(player: ArrayDeque<Int>): Int {
            return player.reversed().foldIndexed(0) { index, score, card -> score + (card * (index + 1)) }
        }
    }

    data class CombatState(val rounds: Int, val winner: Int, val deck: ArrayDeque<Int>, val score: Int)

    data class CombatSnapshot(val round: Int, val player1: List<Int>, val player2: List<Int>)
}
