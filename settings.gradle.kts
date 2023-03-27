pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        jcenter()
        mavenCentral()
        mavenLocal()
        maven {
            setUrl("https://repo1.maven.org/maven2/")
        }
        maven { setUrl("https://jitpack.io") }
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") { from(files("./gradle/versions.toml")) }
    }
}
// ----

rootProject.name = "Desktop_Chat_App"

include(":protos")
include(":generate_protos")
include(":client")