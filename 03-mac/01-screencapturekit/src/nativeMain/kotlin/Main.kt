import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.autoreleasepool
import kotlinx.cinterop.readValue
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.files.SystemTemporaryDirectory
import platform.AppKit.NSBitmapImageFileType
import platform.AppKit.NSBitmapImageRep
import platform.AppKit.representationUsingType
import platform.CoreFoundation.CFRunLoopRun
import platform.CoreGraphics.CGRectNull
import platform.CoreGraphics.CGWindowListCreateImage
import platform.CoreGraphics.kCGWindowImageDefault
import platform.CoreGraphics.kCGWindowListOptionIncludingWindow
import platform.Foundation.NSDictionary
import platform.Foundation.NSURL
import platform.Foundation.dictionary
import platform.Foundation.writeToURL
import platform.ScreenCaptureKit.SCDisplay
import platform.ScreenCaptureKit.SCRunningApplication
import platform.ScreenCaptureKit.SCShareableContent
import platform.ScreenCaptureKit.SCWindow
import platform.posix.exit
import platform.posix.random

@OptIn(BetaInteropApi::class)
fun main() {
    val tempDir = Path(SystemTemporaryDirectory, "kotlin_native_screenshot_${random()}")
    SystemFileSystem.createDirectories(tempDir, true)
    println("Created temporary directory: $tempDir")

    println("Start it.")
    autoreleasepool {
        SCShareableContent.getShareableContentWithCompletionHandler { content: SCShareableContent?, error ->
            if (error != null) {
                println("Error retrieving displays: ${error.localizedDescription}")
                return@getShareableContentWithCompletionHandler
            }

            if (content == null) {
                println("No content found.")
                return@getShareableContentWithCompletionHandler
            }

            if (content.displays.isEmpty()) {
                println("No displays found.")
            } else {
                println("Displays:")
                content.displays.forEach { display ->
                    if (display is SCDisplay) {
                        println("  Display ${display.displayID}: ${display.width}x${display.height} - ${display.description}")
                    }
                }
            }

            val applications = content.applications
            if (applications.isEmpty()) {
                println("No applications found.")
            } else {
                println("Applications:")
                applications.forEachIndexed { _, application ->
                    if (application is SCRunningApplication) {
                        println("  ${application.processID.toString().padStart(8, ' ')}:  ${application.bundleIdentifier} ${application.applicationName}")
                    }
                }
            }

            val windows = content.windows
            if (windows.isEmpty()) {
                println("No windows found.")
            } else {
                println("Windows:")
                windows.forEachIndexed { _, window ->
                    if (window is SCWindow) {
                        println("  ${window.windowID.toString().padStart(8, ' ')}:  ${window.title} onScreen=${window.onScreen} active=${window.active} ${window.description} ${window.owningApplication?.bundleIdentifier}")
                        captureAndSaveWindow(tempDir, window)
                    }
                }
            }

            exit(0)
        }

        // Run the run loop to wait for the completion handler to be called
        CFRunLoopRun()
    }
}

@OptIn(ExperimentalForeignApi::class)
fun captureAndSaveWindow(tempDir: Path, window: SCWindow) {
    val windowID = window.windowID

    val image = CGWindowListCreateImage(
        CGRectNull.readValue(),
        kCGWindowListOptionIncludingWindow,
        windowID,
        kCGWindowImageDefault
    )

    if (image == null) {
        println("Failed to capture window: $windowID")
        return
    }

    val bitmapRep = NSBitmapImageRep(image)
    val pngData = bitmapRep.representationUsingType(NSBitmapImageFileType.NSBitmapImageFileTypePNG, NSDictionary.dictionary())!!
    val path = "$tempDir/window_$windowID.png"
    val url = NSURL.fileURLWithPath(path)
    pngData.writeToURL(url, atomically = true)

    println("Saved screenshot of window $windowID to $path")
}
