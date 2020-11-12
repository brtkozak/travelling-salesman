import base.Chromosome
import base.Selector
import kotlin.random.Random

class TournamentSelector(maximization: Boolean = true, private val tournamentSize: Int) : Selector(maximization) {

    override fun selectPopulation(oldPopulation: List<Chromosome>, newPopulation : MutableList<Chromosome>): List<Chromosome> {
        if (tournamentSize > oldPopulation.size)
            return oldPopulation
        while (newPopulation.size < oldPopulation.size) {
            val tournament = mutableListOf<Chromosome>()
            while (tournament.size < tournamentSize) {
                val participantIndex = Random.nextInt(0, oldPopulation.size - 1)
                tournament.add(oldPopulation[participantIndex])
            }
            val winner = if (maximization)
                tournament.maxBy { it.rate }
            else
                tournament.minBy { it.rate }
            winner?.let {
                newPopulation.add(winner.copy())
            }
        }
        return newPopulation;
    }

}