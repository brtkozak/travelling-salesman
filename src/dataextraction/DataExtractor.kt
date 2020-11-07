package dataextraction

interface DataExtractor <T> {
    fun extractData(path : String) : T?
}