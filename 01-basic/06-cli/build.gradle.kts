plugins {
    kotlin("multiplatform") version "2.0.0"
}

kotlin {
    macosArm64() {
        binaries {
            executable()
        }
    }
    macosX64() {
        binaries {
            executable()
        }
    }
    linuxX64() {
        binaries {
            executable()
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation("com.github.ajalt.clikt:clikt:4.4.0")
            }
        }
    }
}
