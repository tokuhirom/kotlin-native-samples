plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "kotlin-native-samples"

include(":02-sockets:01-echo-server")
include(":03-mac:01-screencapturekit")
