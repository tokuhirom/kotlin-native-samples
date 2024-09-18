plugins {
    kotlin("multiplatform") version "2.0.20"
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
                implementation("org.jetbrains.kotlinx:kotlinx-io-core:0.5.4")
            }
        }
    }
}
