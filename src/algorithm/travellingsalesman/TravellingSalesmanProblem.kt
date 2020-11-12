package algorithm.travellingsalesman

import algorithm.common.Printer
import algorithm.common.mutator.Inversion
import base.*

class TravellingSalesmanProblem(
    populationGenerator: PopulationGenerator,
    data: Array<IntArray>,
    rater: RouteRater,
    selector: Selector,
    crosser: Crosser,
    mutator: Mutator,
    inversion: Mutator? = null,
    exclusivityPercentage: Double = 0.0
) : Problem(populationGenerator, data, rater, selector, crosser, mutator, inversion, exclusivityPercentage) {

    override fun initPopulation() {
        population.clear()
        population = populationGenerator.generatePopulation(
            mapOf(
                Pair(
                    0,
                    makeDataSetBasedOnPoints(data.size)
                )
            )
        ) as MutableList<Chromosome>
    }

    private fun makeDataSetBasedOnPoints(points: Int): List<Int> {
        val dataSet = mutableListOf<Int>()
        for (i in 0 until points) {
            dataSet.add(i)
        }
        return dataSet
    }

    override fun ratePopulation() {
        for (i in population.indices) {
            rater.rate(population[i])
        }
    }

    override fun select() {
        population = selector.selectPopulation(population) as MutableList<Chromosome>
    }

    override fun cross() {
        population = crosser.crossPopulation(population) as MutableList<Chromosome>
    }

    override fun mutate() {
        population = mutator.mutatePopulation(population) as MutableList<Chromosome>
    }

    override fun inverse() {
        inversion?.let {
            population = it.mutatePopulation(population) as MutableList<Chromosome>
        }
    }

    override fun printPopulation(iteration: Int) {
        println("populatoin: $iteration")
        for (i in population.indices) {
            population[i].gens.forEach {
                print("$it ")
            }
            println()
        }
    }

    override fun printPopulationStatistics(iteration: Int) {
        Printer.printPopulationStatistics(population, iteration)
    }
}