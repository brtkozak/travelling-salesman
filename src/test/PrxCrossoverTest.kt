package test

import algorithm.common.crosser.EdgeCrossover
import algorithm.common.crosser.PmxCrossover
import base.Chromosome
import org.junit.jupiter.api.Test


internal class PrxCrossoverTest {

    @Test
    fun orderedCrossTest() {
        val pmxCrossover = PmxCrossover()

        val chromosome1 = Chromosome(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9))
        val chromosome2 = Chromosome(listOf(9, 3, 7, 8, 2, 6, 5, 1, 4))

        val splitPoint1 = 3
        val splitPoint2 = 7

        val pmxCross = PmxCrossover::class.java.getDeclaredMethod(
            "pmxCross",
            Chromosome::class.java,
            Chromosome::class.java,
            Int::class.java,
            Int::class.java
        )
        pmxCross.isAccessible = true
        val child1 = pmxCross.invoke(pmxCrossover, chromosome1, chromosome2, splitPoint1, splitPoint2)
        assert((child1 as Chromosome).gens == listOf(9, 3, 2, 4, 5, 6, 7, 1, 8))
        val child2 = pmxCross.invoke(pmxCrossover, chromosome2, chromosome1, splitPoint1, splitPoint2)
    }

}