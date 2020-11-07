package algorithm.common.selector

import base.Chromosome
import base.Crosser
import base.Selector
import kotlin.random.Random

class RouletteSelector(private val crosser : Crosser, private val maximization: Boolean = true) : Selector() {

    var population: List<Chromosome>? = null
    var ranges: List<Double>? = null

    override fun selectAndCross(population: List<Chromosome>): List<Chromosome> {
        val newPopulation = mutableListOf<Chromosome>()
        val ratings = population.map { if (maximization) it.rate else 1 / it.rate }
        val ratingsSum = ratings.sum()
        val ranges = ratings.map { it / ratingsSum }

        this.ranges = ranges
        this.population = population

        while (newPopulation.size < population.size) {
            val firstParent = select()
            val secondParent = select()
            if(firstParent != null && secondParent != null) {
                val shouldCross = Random.nextDouble(0.0, 1.0)
                if (shouldCross < crossingProbability) {
                    val newChildren = crosser.cross(Pair(firstParent, secondParent))
                    newPopulation.addAll(newChildren.toList())
                } else {
                    newPopulation.addAll(listOf(firstParent, secondParent))
                }
            }
        }

        return newPopulation
    }

    private fun select() : Chromosome? {
        val random = Random.nextDouble(0.0, 1.0)
        ranges?.let { ranges ->
            var sum = 0.0
            for(i in ranges.indices) {
                sum += ranges[i]
                if(random <= sum)
                    return population?.get(i)
            }
        }
        return null
    }

}