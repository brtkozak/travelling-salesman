package test

import algorithm.common.mutator.Inversion
import base.Chromosome
import org.junit.jupiter.api.Test

class InversionTest {

    @Test
    fun inversionTest() {

        val inversion = Inversion()
        val chromosome = Chromosome(listOf(1, 2, 3, 4, 5, 6, 7))
        val firstSplitPoint = 2
        val secondSplitPoint = 5

        val inversionOperation = Inversion::class.java.getDeclaredMethod(
            "inversion",
            Chromosome::class.java,
            Int::class.java,
            Int::class.java
        )
        inversionOperation.isAccessible = true
        inversionOperation.invoke(inversion, chromosome, firstSplitPoint, secondSplitPoint)
        assert(chromosome.gens == listOf(1, 2, 5, 4, 3, 6, 7))
    }

}