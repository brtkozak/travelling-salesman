package algorithm.common.crosser

import base.Chromosome
import base.Crosser
import kotlin.random.Random

class OrderedCrossover : Crosser {

    override fun cross(parents: Pair<Chromosome, Chromosome>): Pair<Chromosome, Chromosome> {
        var firstSplitPoint = Random.nextInt(parents.first.gens.size + 1)
        var secondSplitPoint = Random.nextInt(parents.first.gens.size + 1)

        if (firstSplitPoint > secondSplitPoint) {
            val temp = firstSplitPoint
            firstSplitPoint = secondSplitPoint
            secondSplitPoint = temp
        }

        val firstChildren = orderedCross(parents.first, parents.second, firstSplitPoint, secondSplitPoint)
        val secondChildren = orderedCross(parents.second, parents.first, firstSplitPoint, secondSplitPoint)
        return Pair(firstChildren, secondChildren)
    }

    private fun orderedCross(
        firstParent: Chromosome,
        secondParent: Chromosome,
        firstSplitPoint: Int,
        secondSplitPoint: Int
    ): Chromosome {
        val core = mutableListOf<Int>()
        val beginning = mutableListOf<Int>()
        val ending = mutableListOf<Int>()
        for (i in firstSplitPoint until secondSplitPoint) {
            core.add(firstParent.gens[i])
        }
        var placesAtStart = firstSplitPoint
        for (i in secondSplitPoint until secondParent.gens.size) {
            if (!beginning.contains(secondParent.gens[i]) && !core.contains(secondParent.gens[i]) && !ending.contains(secondParent.gens[i])) {
                if(placesAtStart > 0) {
                    beginning.add(secondParent.gens[i])
                    placesAtStart--
                }
                else
                    ending.add(secondParent.gens[i])
            }
        }
        for (i in 0 until secondSplitPoint) {
            if (!beginning.contains(secondParent.gens[i]) && !core.contains(secondParent.gens[i]) && !ending.contains(secondParent.gens[i])) {
                if(placesAtStart > 0) {
                    beginning.add(secondParent.gens[i])
                    placesAtStart--
                }
                else
                    ending.add(secondParent.gens[i])
            }
        }
        val result = beginning + core + ending
        return Chromosome(result)
    }

}