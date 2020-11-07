package algorithm.travellingsalesman

import base.Chromosome
import base.PopulationGenerator
import kotlin.random.Random

class TravellingSalesManPopulationGenerator(private val populationSize: Int) : PopulationGenerator {

    override fun generatePopulation(
        genDataSets: Map<Int, List<Int>>
    ): List<Chromosome> {
        val routesDataset = mutableListOf<Int>()

        val result = mutableListOf<Chromosome>()
        for (i in 0 until populationSize) {
            val genDataSet = mutableListOf<Int>()
            genDataSets[0]?.forEach {
                genDataSet.add(it)
            }
            val generatedGens = mutableListOf<Int>()
            while (genDataSet.size > 0) {
                var indexToRemove = Random.nextInt(genDataSet.size)
                generatedGens.add(genDataSet[indexToRemove])
                genDataSet.removeAt(indexToRemove)
            }
            val generatedChromosome = Chromosome(generatedGens, 0.0)
            result.add(generatedChromosome)
        }
        return result
    }

}