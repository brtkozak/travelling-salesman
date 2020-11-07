package algorithm

import base.Problem
import base.StopCondition
import chart.ChartDataSet
import chart.ChartDrawer
import chart.Series

class Algorithm(private val problem: Problem, private val stopCondition: StopCondition, private val chartDrawer: ChartDrawer) {

    private val bestChromosomeRateForIterationDataSet =
        ChartDataSet("Best chromosome rate for iteration", "Iteration", "Best chromosome rate")

    fun run(crossingProbability: List<Double>, mutationProbability: List<Double>) {
        crossingProbability.forEach { crossing ->
            mutationProbability.forEach { mutation ->
                problem.selector.crossingProbability = crossing
                problem.mutator.mutationProbability = mutation
                runBaseAlgorithm()
            }
        }
        drawCharts()
    }

    private fun runBaseAlgorithm() {
        problem.initPopulation()
        problem.ratePopulation()
        var iteration = 0
        problem.printPopulationStatistics(iteration)
        initChartDataset()
        updateChartsData(iteration)
        while (stopCondition.stop(problem, iteration)) {
            problem.selectAndCross()
            problem.mutate()
            problem.ratePopulation()
            iteration++
            problem.printPopulationStatistics(iteration)
            updateChartsData(iteration)
        }
    }

    private fun initChartDataset() {
        val seriesMedianRateIterationDataSet = Series("pk: ${problem.selector.crossingProbability} pm: ${problem.mutator.mutationProbability}")
        bestChromosomeRateForIterationDataSet.series.add(seriesMedianRateIterationDataSet)
    }

    private fun updateChartsData(iteration: Int) {
        bestChromosomeRateForIterationDataSet.series.let {
            it[it.size - 1].data.add(Pair(iteration.toDouble(), problem.getPopulationStatistics().best))
        }
    }

    private fun drawCharts() {
        chartDrawer.draw(bestChromosomeRateForIterationDataSet)
    }

}