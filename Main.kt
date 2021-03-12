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

fun main(args: Array<String>) {
    if (args.size == 0) {
        println("Command line argument required!")
        return
    }
    when (args[0]) {
        "test" -> {
            runTests()
        }
        else -> {
            print("Invalid argument!")
        }
    }
}