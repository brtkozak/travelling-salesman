package base

interface PopulationGenerator {
    fun generatePopulation(genDataSets: Map<Int, List<Int>>): List<Chromosome>
}