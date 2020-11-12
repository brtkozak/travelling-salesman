package algorithm.common.selector

import base.Chromosome
import base.Crosser
import base.Selector
import kotlin.random.Random

class RouletteSelector(maximization: Boolean = true) : Selector(maximization) {

    var population: List<Chromosome>? = null
    var ranges: List<Double>? = null

    override fun selectPopulation(oldPopulation: List<Chromosome>, newPopulation : MutableList<Chromosome>): List<Chromosome> {
        val ratings = oldPopulation.map { if (maximization) it.rate else 1 / it.rate }
        val ratingsSum = ratings.sum()
        val ranges = ratings.map { it / ratingsSum }

        this.ranges = ranges
        this.population = oldPopulation

        while (newPopulation.size < oldPopulation.size) {
            select()?.let {
                newPopulation.add(it.copy())
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