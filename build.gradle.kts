plugins {
    kotlin("multiplatform") version "2.0.0" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.6"
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"


allprojects {
    repositories {
        mavenCentral()
    }
}
