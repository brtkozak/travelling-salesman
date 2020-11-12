package test

import algorithm.common.crosser.OrderedCrossover
import base.Chromosome
import org.junit.jupiter.api.Test


internal class OrderedCrossoverTest {

    @Test
    fun orderedCrossTest(){
        val orderedCrossover = OrderedCrossover()

        val chromosome1 = Chromosome(listOf(1, 2, 3, 4, 5))
        val chromosome2 = Chromosome(listOf(5, 4, 3, 2, 1))

        val splitPoint1 = 2
        val splitPoint2 = 4

        val orderedCross = OrderedCrossover::class.java.getDeclaredMethod("orderedCross", Chromosome::class.java, Chromosome::class.java, Int::class.java, Int::class.java)
         orderedCross.isAccessible = true
        val child1 = orderedCross.invoke(orderedCrossover, chromosome1, chromosome2, splitPoint1, splitPoint2)
        assert((child1 as Chromosome).gens == listOf(1, 5, 3, 4, 2))
        val child2 = orderedCross.invoke(orderedCrossover, chromosome2, chromosome1, splitPoint1, splitPoint2)
        assert((child2 as Chromosome).gens == listOf(5, 1, 3, 2, 4))
    }
}