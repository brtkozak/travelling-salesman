package algorithm.common.crosser

import base.Chromosome
import base.Crosser
import kotlin.random.Random

class PmxCrossover : Crosser() {
    override fun cross(parents: Pair<Chromosome, Chromosome>): Pair<Chromosome, Chromosome> {
        var firstSplitPoint = Random.nextInt(parents.first.gens.size + 1)
        var secondSplitPoint = Random.nextInt(parents.first.gens.size + 1)

        if (firstSplitPoint > secondSplitPoint) {
            val temp = firstSplitPoint
            firstSplitPoint = secondSplitPoint
            secondSplitPoint = temp
        }

        val firstChildren = pmxCross(parents.first, parents.second, firstSplitPoint, secondSplitPoint)
        val secondChildren = pmxCross(parents.second, parents.first, firstSplitPoint, secondSplitPoint)
        return Pair(firstChildren, secondChildren)
    }

    private fun pmxCross(
        firstParent: Chromosome,
        secondParent: Chromosome,
        firstSplitPoint: Int,
        secondSplitPoint: Int
    ): Chromosome {
        val gens = mutableListOf<Int>()
        for (i in firstParent.gens.indices)
            gens.add(-1)
        for (i in firstSplitPoint until secondSplitPoint)
            gens[i] = (firstParent.gens[i])
        for (i in firstSplitPoint until secondSplitPoint) {
            val secondParentGen = secondParent.gens[i]
            if (!gens.contains(secondParentGen)) {
                var found = false
                var indexToCheck = i
                while (!found) {
                    val firstParentGen = firstParent.gens[indexToCheck]
                    val indexOfFirstParentGenIsSecondParent = secondParent.gens.indexOf(firstParentGen)
                    if (indexOfFirstParentGenIsSecondParent < firstSplitPoint || indexOfFirstParentGenIsSecondParent >= secondSplitPoint) {
                        found = true
                        gens[indexOfFirstParentGenIsSecondParent] = secondParentGen
                    }
                    else {
                        indexToCheck = indexOfFirstParentGenIsSecondParent
                    }
                }
            }
        }
        for (i in firstParent.gens.indices)
            if (gens[i] == -1)
                gens[i] = secondParent.gens[i]

        return Chromosome(gens)
    }
}