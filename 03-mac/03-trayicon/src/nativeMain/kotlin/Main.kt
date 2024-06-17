import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.autoreleasepool
import platform.AppKit.NSApplication
import platform.AppKit.NSApplicationDelegateProtocol
import platform.AppKit.NSMenu
import platform.AppKit.NSMenuItem
import platform.AppKit.NSStatusBar
import platform.AppKit.NSVariableStatusItemLength
import platform.Foundation.NSNotification
import platform.Foundation.NSSelectorFromString
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
fun main() {
    autoreleasepool {
        val app = NSApplication.sharedApplication()

        val appDelegate =
            object : NSObject(), NSApplicationDelegateProtocol {
                override fun applicationDidFinishLaunching(notification: NSNotification) {
                    val statusItem = NSStatusBar.systemStatusBar.statusItemWithLength(NSVariableStatusItemLength)
                    statusItem.button?.title = "Hello"
                    val menu =
                        NSMenu().apply {
                            addItem(
                                NSMenuItem(
                                    "Quit",
                                    action = NSSelectorFromString("terminate:"),
                                    keyEquivalent = "q",
                                ),
                            )
                        }
                    statusItem.menu = menu
                }
            }

        app.delegate = appDelegate
        app.run()
    }
}
