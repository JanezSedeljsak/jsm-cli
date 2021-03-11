import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

fun main() {
    val currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmm"))
    var uuid = UUID.randomUUID().toString()
    val fileName = "tests_log/log_%s_%s.txt".format(currentDate, uuid)
    val myfile = File(fileName)

    myfile.printWriter().use { out ->
        out.println("tests result") // todo
    }
}