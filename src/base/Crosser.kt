package base

import kotlin.random.Random

abstract class Crosser {

    var crossingProbability : Double = 0.0
    var exclusivityChromosomes : Int = 0

    protected abstract fun cross(parents: Pair<Chromosome, Chromosome>): Pair<Chromosome, Chromosome>

    fun crossPopulation(population: List<Chromosome>): List<Chromosome> {
        val newPopulation = mutableListOf<Chromosome>()
        var oldPopulation = population.sortedBy { it.rate } as MutableList
        newPopulation.addAll(oldPopulation.take(exclusivityChromosomes))
        oldPopulation = oldPopulation.drop(exclusivityChromosomes) as MutableList<Chromosome>
        while (oldPopulation.size > 0) {
            val firstParent = oldPopulation[0]
            val shouldCross = Random.nextDouble(0.0, 1.0)
            if (shouldCross < crossingProbability && oldPopulation.size > 1) {
                val secondParent = oldPopulation[1]
                val children = cross(Pair(firstParent, secondParent))
                newPopulation.addAll(listOf(children.first, children.second))
                oldPopulation.remove(secondParent)
            } else {
                newPopulation.add(firstParent.copy())
            }
            oldPopulation.remove(firstParent)
        }

        return newPopulation
    }
}