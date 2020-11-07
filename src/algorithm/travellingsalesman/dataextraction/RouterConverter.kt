package algorithm.travellingsalesman.dataextraction

import dataextraction.IConverter

class RouterConverter : IConverter {

    override fun convertTextToArray(text: String): Array<IntArray>? {
        var lines = text.split(";")
        lines = lines.filter { it.isNotEmpty() }
        lines = lines.map { it.trimMargin() }

        val result = mutableListOf<IntArray>()
        for (i in lines.indices) {
            val array = IntArray(lines.size)
            val values = lines[i].split(",")
            for (j in lines.indices) {
                try {
                    array[j] = values[j].toInt()
                } catch (e: Exception) {
                    return null
                }
            }
            result.add(array)
        }
        return result.toTypedArray()
    }

}