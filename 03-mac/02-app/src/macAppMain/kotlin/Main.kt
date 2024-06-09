import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreFoundation.CFRunLoopRun
import platform.posix.fclose
import platform.posix.fopen
import platform.posix.fprintf

@OptIn(ExperimentalForeignApi::class)
fun writeDebugInfo(message: String) {
    val file = fopen("/tmp/debug.log", "a")
    if (file != null) {
        fprintf(file, "%s\n", message)
        fclose(file)
    }
}

fun main() {
    println("Hello, world")

    writeDebugInfo("Hello, world\n")

    // Run the run loop to wait for the completion handler to be called
    CFRunLoopRun()
}
