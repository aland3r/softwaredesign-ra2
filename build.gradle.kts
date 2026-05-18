plugins {
    id("java")
    id("application")
}

group = "com.flashbrix"
version = "1.0-SNAPSHOT"

application {
    mainClass = "com.flashbrix.Main"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}