package algorithm.common.mutator

import base.Chromosome
import base.Mutator
import kotlin.random.Random

class Inversion : Mutator() {

    override fun mutate(chromosome: Chromosome) {
        val mutate = Random.nextDouble(1.0)
        if (mutate > mutationProbability)
            return

        var firstGenIndex = Random.nextInt(chromosome.gens.size)
        var secondGenIndex = Random.nextInt(chromosome.gens.size)
        if(firstGenIndex > secondGenIndex) {
            val temp = firstGenIndex
            secondGenIndex = firstGenIndex
            firstGenIndex = temp
        }

        inversion(chromosome, firstGenIndex, secondGenIndex)
    }

    private fun inversion(chromosome: Chromosome, firstGenIndex : Int, secondGenIndex : Int) {
        val beginning = chromosome.gens.take(firstGenIndex)
        val core = chromosome.gens.subList(firstGenIndex, secondGenIndex).reversed()
        val end = chromosome.gens.takeLast(chromosome.gens.size - secondGenIndex)

        val newGens = beginning + core + end
        chromosome.gens = newGens
    }
}