package dataextraction

interface IConverter {
    fun convertTextToArray(text: String): Array<IntArray>?
}