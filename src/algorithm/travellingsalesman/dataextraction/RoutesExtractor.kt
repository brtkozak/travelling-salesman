package algorithm.travellingsalesman.dataextraction

import dataextraction.DataExtractor
import dataextraction.FileReader
import dataextraction.IConverter

class RoutesExtractor(val fileReader: FileReader, val converter: IConverter) :
    DataExtractor<Array<IntArray>> {

    override fun extractData(path: String): Array<IntArray>? {
        val redText = fileReader.readFile(path)
        return converter.convertTextToArray(redText)
    }

}