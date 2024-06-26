import kotlinx.cinterop.ExperimentalForeignApi
import platform.AppKit.NSApplication
import platform.AppKit.NSApplicationDelegateProtocol
import platform.AppKit.NSMenu
import platform.AppKit.NSMenuItem
import platform.AppKit.NSStatusBar
import platform.AppKit.NSStatusItem
import platform.AppKit.NSVariableStatusItemLength
import platform.Foundation.NSNotification
import platform.Foundation.NSSelectorFromString
import platform.darwin.NSObject

class TrayIconHandler {
    // keep these properties as fields to avoid being garbage collected.
    private lateinit var statusItem: NSStatusItem
    private lateinit var appDelegate: NSApplicationDelegateProtocol

    @OptIn(ExperimentalForeignApi::class)
    fun createAppDelegate(): NSApplicationDelegateProtocol {
        appDelegate =
            object : NSObject(), NSApplicationDelegateProtocol {
                override fun applicationDidFinishLaunching(notification: NSNotification) {
                    statusItem = NSStatusBar.systemStatusBar.statusItemWithLength(NSVariableStatusItemLength)
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
        return appDelegate
    }
}

fun main() {
    val trayIconHandler = TrayIconHandler()
    val app = NSApplication.sharedApplication()
    app.delegate = trayIconHandler.createAppDelegate()
    app.run()
}
