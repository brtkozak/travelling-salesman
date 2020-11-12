import algorithm.Algorithm
import algorithm.common.IterationStopCondition
import algorithm.common.crosser.EdgeCrossover
import algorithm.common.crosser.OrderedCrossover
import algorithm.common.crosser.PmxCrossover
import algorithm.common.mutator.Inversion
import algorithm.common.mutator.SingleSwapMutator
import algorithm.common.selector.RankingSelector
import algorithm.common.selector.RouletteSelector
import algorithm.travellingsalesman.RouteRater
import algorithm.travellingsalesman.TravellingSalesManPopulationGenerator
import algorithm.travellingsalesman.dataextraction.RouterConverter
import dataextraction.FileReader
import algorithm.travellingsalesman.dataextraction.RoutesExtractor
import algorithm.travellingsalesman.TravellingSalesmanProblem
import chart.LineChart
import org.junit.jupiter.api.Order

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            solveTravellingSalesmanProblem("data2.txt")
        }

        private fun solveTravellingSalesmanProblem(path: String) {
            val data = RoutesExtractor(
                FileReader(),
                RouterConverter()
            ).extractData(path)

            data?.let {
                val populationSize = 300
                val maxIteration = 3000
                val crossingProbabilities = listOf(0.7, 0.8)
                val mutationProbability = listOf(0.08, 0.05)
                val inversionProbability = 0.4
                val exclusivityPercentage = 0.05

                val problem = TravellingSalesmanProblem(
                    TravellingSalesManPopulationGenerator(populationSize),
                    it,
                    RouteRater(it),
                    TournamentSelector(false, 3),
                    EdgeCrossover(),
                    SingleSwapMutator(),
                    Inversion(),
                    exclusivityPercentage
                )
                val algorithm = Algorithm(problem, IterationStopCondition(maxIteration), LineChart())
                algorithm.run(crossingProbabilities, mutationProbability, inversionProbability)
            }
        }
    }
}