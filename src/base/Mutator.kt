package base

abstract class Mutator  {

    var mutationProbability: Double = 0.0
    var exclusivityChromosomes : Int = 0

    protected abstract fun mutate(chromosome: Chromosome)

    fun mutatePopulation(population : List<Chromosome>) : List<Chromosome>{
        val oldPopulation = population.sortedBy { it.rate }
        val newPopulation = mutableListOf<Chromosome>()
        newPopulation.addAll(oldPopulation.take(exclusivityChromosomes))
        for(i in exclusivityChromosomes until oldPopulation.size) {
            mutate(oldPopulation[i])
            newPopulation.add(oldPopulation[i])
        }
        return newPopulation
    }

}