plugins {
    kotlin("multiplatform") version "2.0.21"
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
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
            }
        }
    }
}
