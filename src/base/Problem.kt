package base

import algorithm.travellingsalesman.RouteRater

abstract class Problem(
    val populationGenerator: PopulationGenerator,
    val data: Array<IntArray>,
    val rater: RouteRater,
    val selector: Selector,
    val crosser: Crosser,
    val mutator: Mutator,
    val inversion: Mutator?,
    private val exclusivityPercentage : Double = 0.0
) {

    init {
        val exclusivityChromosomes =  (populationGenerator.populationSize * exclusivityPercentage).toInt()
        selector.exclusivityChromosomes = exclusivityChromosomes
        crosser.exclusivityChromosomes = exclusivityChromosomes
        mutator.exclusivityChromosomes = exclusivityChromosomes
        inversion?.exclusivityChromosomes = exclusivityChromosomes
    }

    var population: MutableList<Chromosome> = mutableListOf()

    abstract fun initPopulation()
    abstract fun ratePopulation()
    abstract fun select()
    abstract fun cross()
    abstract fun printPopulation(iteration: Int)
    abstract fun printPopulationStatistics(iteration: Int)
    abstract fun mutate()
    abstract fun inverse()

    fun getCrossingProbability() = crosser.crossingProbability
    fun getMutationProbability() = mutator.mutationProbability
    fun getInversionProbability() = inversion?.mutationProbability

    fun setMutationProbability(mutationProbability: Double) {
        mutator.mutationProbability = mutationProbability
    }

    fun setCrossingProbability(crossingProbability: Double) {
        crosser.crossingProbability = crossingProbability
    }

    fun setInversionProbability(inversionProbability: Double) {
        inversion?.mutationProbability = inversionProbability
    }

    fun getPopulationStatistics(): PopulationStatistics {
        var best = population[0].rate
        var worst = population[0].rate
        var sum = 0.0
        population.forEach {
            if (it.rate < best)
                best = it.rate
            else if (it.rate > worst)
                worst = it.rate
            sum += it.rate
        }
        val average = sum / population.size
        var median = 0.0
        population.sortedBy { it.rate }

        median = if (population.size % 2 == 0) {
            (population[population.size / 2].rate + population[population.size / 2 + 1].rate) / 2
        } else {
            population[population.size].rate
        }

        return PopulationStatistics(average, median, best, worst)
    }

}