package algorithm.common.mutator

import base.Chromosome
import base.Mutator
import kotlin.random.Random

class SingleSwapMutator : Mutator() {

    override fun mutate(chromosome: Chromosome) {
        val mutate = Random.nextDouble(1.0)
        if (mutate > mutationProbability)
            return

        val firstGenIndex = Random.nextInt(chromosome.gens.size)
        val secondGenIndex = Random.nextInt(chromosome.gens.size)

        val temp = chromosome.gens[firstGenIndex]
        (chromosome.gens as MutableList)[firstGenIndex] = chromosome.gens[secondGenIndex]
        (chromosome.gens as MutableList)[secondGenIndex] = temp
    }
}