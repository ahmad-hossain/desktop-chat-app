plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm")

    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use the Kotlin JUnit 5 integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    // Use the JUnit 5 integration.
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.1")

    // This dependency is used by the application.
    implementation("com.google.guava:guava:31.1-jre")

    implementation(project(":generate_protos"))
    implementation("io.grpc:grpc-netty-shaded:1.54.0")
}

application {
// Define the main class for the application.
    mainClass.set("ChatServerKt")
}

tasks.named<Test>("test") {
// Use JUnit Platform for unit tests.
    useJUnitPlatform()
}
