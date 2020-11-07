import algorithm.Algorithm
import algorithm.common.IterationStopCondition
import algorithm.common.crosser.OrderedCrossover
import algorithm.common.selector.RouletteSelector
import algorithm.common.SingleSwapMutator
import algorithm.travellingsalesman.RouteRater
import algorithm.travellingsalesman.TravellingSalesManPopulationGenerator
import algorithm.travellingsalesman.dataextraction.RouterConverter
import dataextraction.FileReader
import algorithm.travellingsalesman.dataextraction.RoutesExtractor
import algorithm.travellingsalesman.TravellingSalesmanProblem
import chart.LineChart

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            solveTravellingSalesmanProblem("data.txt")
        }

        private fun solveTravellingSalesmanProblem(path : String) {
            val data = RoutesExtractor(
                FileReader(),
                RouterConverter()
            ).extractData(path)

            data?.let {
                val populationSize = 200
                val maxIteration = 3000
                val crossingProbabilities = listOf(0.65, 0.8)
                val mutationProbability = listOf(0.05, 0.01)

                val problem = TravellingSalesmanProblem(TravellingSalesManPopulationGenerator(populationSize), it, RouteRater(it),
                    RouletteSelector(OrderedCrossover(), false), SingleSwapMutator())
                val algorithm = Algorithm(problem, IterationStopCondition(maxIteration), LineChart())
                algorithm.run(crossingProbabilities, mutationProbability)
            }
        }
    }
}