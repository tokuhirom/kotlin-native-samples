plugins {
    kotlin("multiplatform") version "2.1.0"
}

kotlin {
    macosArm64("native") {
        binaries {
            executable()
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-io-core:0.6.0")
            }
        }
    }
}
