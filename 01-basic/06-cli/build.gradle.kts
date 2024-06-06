plugins {
    kotlin("multiplatform") version "2.0.0"
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
                implementation("com.github.ajalt.clikt:clikt:4.4.0")
            }
        }
    }
}
