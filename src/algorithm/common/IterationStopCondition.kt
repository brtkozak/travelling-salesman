package algorithm.common

import base.Problem
import base.StopCondition
import kotlin.math.max

class IterationStopCondition(private val maxIteration : Int) : StopCondition {

    override fun stop(problem: Problem, iteration: Int): Boolean {
            return iteration < maxIteration
    }

}