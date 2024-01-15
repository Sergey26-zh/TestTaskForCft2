plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("commons-cli:commons-cli:1.4")
    implementation("org.projectlombok:lombok:1.18.26")
    annotationProcessor("org.projectlombok:lombok:1.18.26")
    testImplementation ("org.mockito:mockito-core:5.8.0")
    testImplementation ("org.mockito:mockito-junit-jupiter:5.8.0")
    implementation("commons-io:commons-io:2.11.0")
}

tasks.test {
    useJUnitPlatform()
}