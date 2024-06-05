import kotlinx.cinterop.autoreleasepool
import platform.CoreFoundation.CFRunLoopRun
import platform.ScreenCaptureKit.SCDisplay
import platform.ScreenCaptureKit.SCShareableContent
import platform.posix.exit

class DisplayManager {
    fun listDisplays() {
        SCShareableContent.getShareableContentWithCompletionHandler { content: SCShareableContent?, error ->
            if (error != null) {
                println("Error retrieving displays: ${error.localizedDescription}")
                return@getShareableContentWithCompletionHandler
            }

            @Suppress("UNCHECKED_CAST")
            val displays: List<SCDisplay> = (content?.displays ?: emptyList<SCDisplay>()) as List<SCDisplay>
            if (displays.isEmpty()) {
                println("No displays found.")
            } else {
                println("Available displays:")
                displays.forEachIndexed { _, display ->
                    println("Display ${display.displayID}: ${display.width}x${display.height} - ${display.description}")
                }
            }

            exit(0)
        }

        // Run the run loop to wait for the completion handler to be called
        CFRunLoopRun()
    }
}

@OptIn(kotlinx.cinterop.BetaInteropApi::class)
fun main() {
    println("Start it.")
    autoreleasepool {
        val displayManager = DisplayManager()
        displayManager.listDisplays()
    }
}

