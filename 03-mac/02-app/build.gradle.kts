plugins {
    kotlin("multiplatform") version "2.0.0"
}

kotlin {
    macosX64("macApp") {
        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-io-core:0.3.5")
            }
        }
    }
}

tasks {
    val createAppDirStructure by creating(Copy::class) {
        group = "build"
        description = "Creates the macOS app directory structure"
        from("src/macAppMain/resources") {
            include("**/*")
        }
        into("$buildDir/MyKotlinApp.app/Contents/Resources")
        doLast {
            mkdir("$buildDir/MyKotlinApp.app/Contents/MacOS")
        }
    }

    val copyExecutable by creating(Copy::class) {
        group = "build"
        description = "Copies the executable to the app bundle"
        dependsOn("linkReleaseExecutableMacApp")
        from("$buildDir/bin/macosX64/releaseExecutable/macApp.kexe") {
            into("$buildDir/MyKotlinApp.app/Contents/MacOS")
            rename { "MyKotlinApp" }
        }
        doLast {
            println("Executable copied to: ${file("$buildDir/MyKotlinApp.app/Contents/MacOS/MyKotlinApp").absolutePath}")
        }
    }

    val generatePlist by creating {
        group = "build"
        description = "Generates the Info.plist file"
        dependsOn(createAppDirStructure) // Ensure directory structure is created first
        doLast {
            mkdir("$buildDir/MyKotlinApp.app/Contents/MacOS")
            val plistFile = file("$buildDir/MyKotlinApp.app/Contents/Info.plist")
            plistFile.writeText(
                """
                <?xml version="1.0" encoding="UTF-8"?>
                <!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
                <plist version="1.0">
                <dict>
                    <key>CFBundleName</key>
                    <string>MyKotlinApp</string>
                    <key>CFBundleDisplayName</key>
                    <string>MyKotlinApp</string>
                    <key>CFBundleExecutable</key>
                    <string>MyKotlinApp</string>
                    <key>CFBundleIdentifier</key>
                    <string>com.example.mykotlinapp</string>
                    <key>CFBundleVersion</key>
                    <string>1.0</string>
                    <key>CFBundlePackageType</key>
                    <string>APPL</string>
                    <key>CFBundleSignature</key>
                    <string>????</string>
                    <key>CFBundleInfoDictionaryVersion</key>
                    <string>6.0</string>
                </dict>
                </plist>
                """.trimIndent()
            )
        }
    }

    val createMacAppBundle by creating {
        group = "build"
        description = "Creates the macOS app bundle"
        dependsOn(createAppDirStructure, copyExecutable, generatePlist)
    }
}

tasks.getByName("build").dependsOn("createMacAppBundle")
