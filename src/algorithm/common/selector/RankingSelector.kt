package algorithm.common.selector

import base.Chromosome
import base.Selector
import kotlin.random.Random

class RankingSelector(maximization : Boolean = false) : Selector(maximization) {

    var population: List<Chromosome>? = null
    var ranges: List<Double>? = null

    override fun selectPopulation(oldPopulation: List<Chromosome>, newPopulation: MutableList<Chromosome>): List<Chromosome> {
        var sortedPopulation = oldPopulation.sortedBy { it.rate }
        if(!maximization)
            sortedPopulation = sortedPopulation.reversed()
        var sumOfRankings = 0.0
        for(i in 1 .. sortedPopulation.size){
            sumOfRankings += i
        }
        val ranges = mutableListOf<Double>()
        for(i in sortedPopulation.indices) {
            ranges.add((i+1)/sumOfRankings)
        }

        this.ranges = ranges
        this.population = sortedPopulation

        while (newPopulation.size < oldPopulation.size) {
            select()?.let {
                newPopulation.add(it.copy())
            }
        }
        return newPopulation
    }

    private fun select() : Chromosome? {
        val random = 1 - Random.nextDouble(0.0, 1.0)
        ranges?.let { ranges ->
            var sum = 0.0
            for(i in ranges.size - 1 downTo 0) {
                sum += ranges[i]
                if(sum >= random)
                    return population?.get(i)
            }
        }
        return null
    }

}