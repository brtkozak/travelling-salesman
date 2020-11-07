package base

interface Crosser {

    fun cross(parents : Pair<Chromosome, Chromosome>) : Pair<Chromosome, Chromosome>

}