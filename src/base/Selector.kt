package base

abstract class Selector(val maximization : Boolean) {

    var exclusivityChromosomes : Int = 0

    fun selectPopulation(population : List<Chromosome>) : List<Chromosome> {
        val newPopulation = mutableListOf<Chromosome>()
        newPopulation.addAll(population.sortedBy { it.rate }.take(exclusivityChromosomes))
        return selectPopulation(population, newPopulation)
    }

    protected abstract fun selectPopulation(oldPopulation : List<Chromosome>, newPopulation : MutableList<Chromosome>) : List<Chromosome>

}