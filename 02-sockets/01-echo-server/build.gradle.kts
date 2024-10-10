plugins {
    kotlin("multiplatform") version "2.0.21"
}

kotlin {
    val isAarch64 = System.getProperty("os.arch").contains("aarch64")
    if (isAarch64) {
        macosArm64("native") {
            binaries {
                executable()
            }
        }
    } else {
        macosX64("native") {
            binaries {
                executable()
            }
        }
    }

    sourceSets {
    }
}
