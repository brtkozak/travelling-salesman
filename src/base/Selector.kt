package base

abstract class Selector {

    var crossingProbability : Double = 0.0

    abstract fun selectAndCross(population : List<Chromosome>) : List<Chromosome>
}