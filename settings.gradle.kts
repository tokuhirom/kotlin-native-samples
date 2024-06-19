plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "kotlin-native-samples"

include(":01-basic:06-cli")
include(":01-basic:07-workers")

include(":02-sockets:01-echo-server")

include(":03-mac:01-screencapturekit")
include(":03-mac:03-trayicon")
