package algorithm.common.crosser

import base.Chromosome
import base.Crosser
import kotlin.random.Random

class EdgeCrossover : Crosser() {

    private val neighbors: MutableMap<Int, MutableList<Neighbor>> = mutableMapOf()

    override fun cross(parents: Pair<Chromosome, Chromosome>): Pair<Chromosome, Chromosome> {
        return Pair(edgeCross(parents), edgeCross(parents))
    }

    fun edgeCross (parents: Pair<Chromosome, Chromosome>) : Chromosome {
        neighbors.clear()
        for (i in parents.first.gens.indices) {
            neighbors[parents.first.gens[i]] = mutableListOf()
        }
        for (i in parents.first.gens.indices) {
            if (i == 0 && parents.first.gens.size > 1)
                neighbors[parents.first.gens[i]]?.add(Neighbor(parents.first.gens[i + 1]))
            else {
                if (i > 0) {
                    neighbors[parents.first.gens[i]]?.add(Neighbor(parents.first.gens[i - 1]))
                }
                if (i < parents.first.gens.size - 1)
                    neighbors[parents.first.gens[i]]?.add(Neighbor(parents.first.gens[i + 1]))
            }
        }
        for (i in parents.second.gens.indices) {
            if(i == 0 && parents.second.gens.size > 1)
                neighbors[parents.second.gens[i]]?.add(Neighbor(parents.first.gens[i + 1]))
            else {
                if (i > 0) {
                    neighbors[parents.second.gens[i]]?.firstOrNull { it.number == parents.second.gens[i - 1] }.let {
                        if (it != null) {
                            it.common = true
                        } else {
                            neighbors[parents.second.gens[i]]?.add(Neighbor(parents.second.gens[i - 1]))
                        }
                    }
                }
                if (i < parents.second.gens.size - 1) {
                    neighbors[parents.second.gens[i]]?.firstOrNull { it.number == parents.second.gens[i + 1] }.let {
                        if (it != null) {
                            it.common = true
                        } else {
                            neighbors[parents.second.gens[i]]?.add(Neighbor(parents.second.gens[i + 1]))
                        }
                    }
                }
            }
        }

        val gens = mutableListOf<Int>()
        val randomGen = Random.nextInt(parents.first.gens.size - 1)
        gens.add(randomGen)
        clearNeighbors(randomGen)

        while (gens.size < parents.first.gens.size) {
            val lastItemNeighbors = neighbors[gens.last()]
            if(lastItemNeighbors?.isNotEmpty() == true) {
                if(lastItemNeighbors.firstOrNull { it.common } != null) {
                    lastItemNeighbors.firstOrNull { it.common }?.let {
                        gens.add(it.number)
                        clearNeighbors(it.number)
                    }
                }
                else {
                    val neighborWithSmallestList = neighbors.filterKeys { it -> lastItemNeighbors.map { it.number }.contains(it) }.minBy { it.value.size }
                    neighborWithSmallestList?.let {
                        gens.add(it.key)
                        clearNeighbors(it.key)
                    }
                }
            }
            else {
                var found = false
                while (!found) {
                    val left = neighbors.filterKeys { !gens.contains(it) }.map {it.key }
                    val randIndex = if(left.size > 1) Random.nextInt( left.size - 1) else 0
                    val randGen = left[randIndex]
                    if(!gens.contains(randGen)) {
                        gens.add(randGen)
                        clearNeighbors(randGen)
                        found = true
                    }
                }
            }
        }
        return Chromosome(gens)
    }

    fun clearNeighbors(toClear : Int){
        neighbors.forEach { _, u ->
            u.removeIf { it.number == toClear }
        }
    }

    data class Neighbor(
        val number: Int,
        var common: Boolean = false
    )

}