plugins {
    id("java")
}

group = "com.github.sfidencio"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    //reactor
    implementation("io.projectreactor:reactor-core:3.4.0")
}

tasks.test {
    useJUnitPlatform()
}