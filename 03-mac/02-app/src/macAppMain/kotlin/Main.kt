import platform.CoreFoundation.CFRunLoopRun

fun main() {
    println("Hello, world")

    // Run the run loop to wait for the completion handler to be called
    CFRunLoopRun()
}
