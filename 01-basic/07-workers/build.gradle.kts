plugins {
    kotlin("multiplatform") version "2.0.10"
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
            }
        }
    }
}
