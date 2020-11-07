package algorithm.travellingsalesman

import base.Chromosome
import base.ChromosomeRater

class RouteRater(private val data : Array<IntArray>) : ChromosomeRater {

    override fun rate(chromosome: Chromosome) {
        var rate = 0.0
        val routeGen = chromosome.gens
        for(i in 0 until data.size -1) {
            rate += data[routeGen[i]][routeGen[i+1]]
        }
        if(routeGen.size > 1) {
            rate += data[routeGen.size -1][routeGen[0]]
        }
        chromosome.rate = rate
    }
}