@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    kotlin("jvm") version "1.8.0"
    application
//    alias(libs.plugins.kotlin) apply false
}

group = "dev.baseio.slackclone"
version = "1.0"

subprojects {
//    apply(plugin = rootProject.libs.plugins.ktlint.get().pluginId)
}

fun teamPropsFile(propsFile: String): File {
    val teamPropsDir = file("team-props")
    return File(teamPropsDir, propsFile)
}

allprojects {
    repositories {
        google()
        gradlePluginPortal()
        mavenLocal()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
}
//plugins {
//    kotlin("jvm") version "1.8.0"
//    application
//}
//
//group = "org.example"
//version = "1.0-SNAPSHOT"
//
//repositories {
//    mavenCentral()
//}
//
//dependencies {
//    testImplementation(kotlin("test"))
//}
//
//tasks.test {
//    useJUnitPlatform()
//}
//
//kotlin {
//    jvmToolchain(19)
//}
//
//application {
//    mainClass.set("MainKt")
//}