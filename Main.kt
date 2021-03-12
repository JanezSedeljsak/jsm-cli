import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

fun runTests() {
    val currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmm"))
    var uuid = UUID.randomUUID().toString()
    val fileName = "../tests_log/log_%s_%s.txt".format(currentDate, uuid)
    val myfile = File(fileName)

    myfile.printWriter().use { out ->
        out.println("tests result") // todo
    }
}

fun runGenerateTemplate(templateName: String) {
    val fileName = "../templates/%s.md".format(templateName)
    val myfile = File(fileName)

    var boilerPlateLines = """
        # Markdown demo....
        ## This is a title
        ### This is a smaller title
        <table>
            <tr>
                <th>this is a table header</th>
                <th>another table header</th>
            </tr>
            <tr>
                <td>this is a table cell</td>
                <td>this is another cell</td>
            </tr>
        </table>
        
        - list item
        - another list item
        
        <font color="green"> Some green text </font>
        
        ### this is an embeded code block
        ```cpp
        #include <iostream>
        
        int main() {
            std::cout << "Hello World from C++!";
            return 0;
        }
        ```
    """.trimIndent().split("\n").toTypedArray()
    

    myfile.printWriter().use { out -> 
        for(line in boilerPlateLines) {
            out.println(line)
        }
    }
}

fun main(args: Array<String>) {
    if (args.size == 0) {
        println("Command line argument required!")
        return
    }
    when (args[0]) {
        "test" -> runTests()
        "generate-template" -> {
            var templateName: String = UUID.randomUUID().toString()
            println(args[1])
            if (args.size > 1 && !args[1].isNullOrEmpty()) {
                templateName = args[1]
            }
            runGenerateTemplate(templateName)
        }
        else -> print("Invalid argument!")
    }
}