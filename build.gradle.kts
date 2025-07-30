import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    java
    id("org.springframework.boot") version "3.5.4"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "io.github.jk6841"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
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
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    runtimeOnly("io.asyncer:r2dbc-mysql")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    runtimeOnly("io.netty:netty-resolver-dns-native-macos:4.2.3.Final:osx-aarch_64")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register<BootRun>("api-app") {
    mainClass.set("io.github.jk6841.kakaoenterprise.ApiApplication")
    classpath = sourceSets["main"].runtimeClasspath
    description = "run api application"
}

tasks.register<BootRun>("dataload-app") {
    mainClass.set("io.github.jk6841.kakaoenterprise.DataLoadApplication")
    classpath = sourceSets["main"].runtimeClasspath
    description = "run dataload application"
}
