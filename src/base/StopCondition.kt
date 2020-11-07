package base

interface StopCondition {
    fun stop(problem : Problem, iteration: Int) : Boolean

}