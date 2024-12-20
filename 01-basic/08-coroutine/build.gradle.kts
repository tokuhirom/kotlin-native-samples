plugins {
    kotlin("multiplatform") version "2.1.0"
}

kotlin {
    macosX64 {
        binaries {
            executable()
        }
    }
    macosArm64 {
        binaries {
            executable()
        }
    }
    linuxX64 {
        binaries {
            executable()
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")
            }
        }
    }
}
