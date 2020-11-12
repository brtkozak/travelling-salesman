package algorithm.common.crosser

import base.Chromosome
import base.Crosser
import kotlin.random.Random

class SinglePointCrossover : Crosser() {

    override fun cross(parents: Pair<Chromosome, Chromosome>): Pair<Chromosome, Chromosome> {
        val dividePoint = Random.nextInt(parents.first.gens.size + 1)
        val firstParentSplit = Pair<MutableList<Int>, MutableList<Int>>(mutableListOf(), mutableListOf())
        parents.first.gens.let {
            firstParentSplit.first.addAll(it.take(dividePoint))
            firstParentSplit.second.addAll(it.takeLast(it.size - dividePoint))
        }
        val secondParentSplit = Pair<MutableList<Int>, MutableList<Int>>(mutableListOf(), mutableListOf())
        parents.second.gens.let {
            secondParentSplit.first.addAll(it.take(dividePoint))
            secondParentSplit.second.addAll(it.takeLast(it.size - dividePoint))
        }

        val firstChild = Chromosome(firstParentSplit.first + secondParentSplit.second)
        val secondChild = Chromosome(secondParentSplit.first + firstParentSplit.second)
        return Pair(firstChild, secondChild)
    }

}