package base

class Chromosome(var gens: List<Int>, var rate: Double = 0.0) {

    fun copy() : Chromosome{
        val copy = Chromosome(mutableListOf())
        (copy.gens as MutableList).addAll(gens)
        copy.rate = rate
        return copy
    }

}

