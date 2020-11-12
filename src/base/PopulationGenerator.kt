package base

abstract class PopulationGenerator(val populationSize : Int) {
    abstract fun generatePopulation(genDataSets: Map<Int, List<Int>>): List<Chromosome>
}