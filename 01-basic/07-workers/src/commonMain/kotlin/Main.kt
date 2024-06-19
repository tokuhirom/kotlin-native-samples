import kotlinx.cinterop.ExperimentalForeignApi
import kotlin.native.concurrent.TransferMode
import kotlin.native.concurrent.Worker

@ExperimentalForeignApi
fun main() {
    // Start a new worker
    val worker = Worker.start()

    // Schedule a task for execution on the worker
    worker.execute(TransferMode.SAFE, {}) {
        // Print the thread identifier of the current thread
        println("Hello from a background worker!")
    }

    // request termination of the worker
    worker.requestTermination(true).result
}
