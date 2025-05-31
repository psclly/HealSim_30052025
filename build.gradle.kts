plugins {
    kotlin("jvm") version "1.9.10"
    kotlin("plugin.serialization") version "1.9.10"
    id("org.jetbrains.compose") version "1.5.10"

}


repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    // add other dependencies you need here

    implementation("io.ktor:ktor-client-core:2.3.4")
    implementation("io.ktor:ktor-client-cio:2.3.4") // CIO engine for HTTP
    implementation("io.ktor:ktor-client-content-negotiation:2.3.4")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.4") // JSON parsing

    //logger
    implementation("org.slf4j:slf4j-simple:2.0.9")
    implementation(compose.desktop.currentOs)
}


tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

compose.desktop {
    application {
        mainClass = "MainKt" // <- make sure this matches your main file
    }
}