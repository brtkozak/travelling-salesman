package base

abstract class Mutator  {

    var mutationProbability: Double = 0.0
    abstract fun mutate(chromosome: Chromosome)

}