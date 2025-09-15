plugins {
    java
    id("org.springframework.boot") version "3.5.5"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("jvm")
    id("org.asciidoctor.jvm.convert") version "4.0.2"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

val asciidoctorExt by configurations.creating

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.h2database:h2")

    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation(kotlin("stdlib-jdk8"))

    asciidoctorExt("org.springframework.restdocs:spring-restdocs-asciidoctor:3.0.0")
}

val snippetsDir = file("build/generated-snippets")

tasks.asciidoctor {
    inputs.dir(snippetsDir)
    configurations(asciidoctorExt)
    dependsOn(tasks.test)
    sources{
        include("**/*.adoc")
    }
    outputs.dir(file("${layout.buildDirectory}/docs/asciidoc"))

}

tasks.withType<Test> {
    useJUnitPlatform()
    outputs.dir(snippetsDir)
}
