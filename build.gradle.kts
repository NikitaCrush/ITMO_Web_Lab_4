import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
    id("org.graalvm.buildtools.native") version "0.9.28"
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.spring") version "1.9.21"
    kotlin("plugin.jpa") version "1.8.0"
    id("com.github.johnrengelman.shadow") version "7.1.0"


}

group = "org.bogdans"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:3.2.2")
    implementation ("org.springframework.boot:spring-boot-starter-websocket")
    implementation("org.springframework:spring-webmvc:6.1.2")


    implementation("org.springframework.boot:spring-boot-starter-security:3.2.1")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.0.4")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.0.4")
//    implementation("org.springframework.boot:spring-boot-data-jpa")

    implementation ("org.springframework.boot:spring-boot-starter-data-jdbc")


    developmentOnly("org.springframework.boot:spring-boot-devtools:3.0.4")
    runtimeOnly("org.springframework.boot:spring-boot-starter-tomcat:3.1.0")
    implementation("org.springframework.security:spring-security-crypto:6.0.2")





    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.10")
    compileOnly("org.projectlombok:lombok:1.18.26")
    annotationProcessor("org.projectlombok:lombok:1.18.26")
    annotationProcessor ("org.springframework.boot:spring-boot-configuration-processor")


    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    compileOnly("jakarta.servlet:jakarta.servlet-api:6.0.0")


    implementation ("io.jsonwebtoken:jjwt-api:0.11.2")
    runtimeOnly ("io.jsonwebtoken:jjwt-impl:0.11.2")
    runtimeOnly ("io.jsonwebtoken:jjwt-jackson:0.11.2")


    implementation("org.postgresql:postgresql:42.5.4")
    implementation("org.hibernate:hibernate-core:6.1.7.Final")


}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "21"
    }
}


tasks.shadowJar {
    archiveClassifier.set("")
    manifest {
        attributes["Main-Class"] = "org.bogdans.ApplicationKt"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
