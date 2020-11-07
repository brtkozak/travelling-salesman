package algorithm.travellingsalesman

import algorithm.common.Printer
import base.*

class TravellingSalesmanProblem(
    populationGenerator: PopulationGenerator,
    data: Array<IntArray>,
    rater: RouteRater,
    selector: Selector,
    mutator: Mutator
) : Problem(populationGenerator, data, rater, selector, mutator) {

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

    override fun selectAndCross() {
        population = selector.selectAndCross(population) as MutableList<Chromosome>
    }

    override fun mutate() {
        population.forEach {
            mutator.mutate(it)
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