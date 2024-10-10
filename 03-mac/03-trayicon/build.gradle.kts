plugins {
    kotlin("multiplatform") version "2.0.21"
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
            }
        }
    }
}
