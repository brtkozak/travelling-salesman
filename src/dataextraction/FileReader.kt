package dataextraction

import java.io.File

class FileReader () : IFileReader{

    override fun readFile(path: String) : String {
        return File(path).readText()
    }

}