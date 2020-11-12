package test

import algorithm.common.crosser.EdgeCrossover
import base.Chromosome
import org.junit.jupiter.api.Test

internal class EdgeCrosserTest  {

    @Test
    fun edgeCrosserTest() {
        val edgeCrossover = EdgeCrossover()

        val chromosome1 = Chromosome(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9))
        val chromosome2 = Chromosome(listOf(9, 8, 7, 3, 2, 6, 5, 1, 4))

        val edgeCross = EdgeCrossover::class.java.getDeclaredMethod(
            "edgeCross",
            Pair::class.java
        )
        edgeCross.isAccessible = true
        val child1 = edgeCross.invoke(edgeCrossover, Pair(chromosome1, chromosome2))
        val child2 = edgeCross.invoke(edgeCrossover, Pair(chromosome1, chromosome2))
    }
}