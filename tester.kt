import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main() {
    val currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmm"))
    val fileName = "tests_log/log_$currentDate.txt"
    val myfile = File(fileName)

    myfile.printWriter().use { out ->
        out.println("tests result") // todo
    }
}