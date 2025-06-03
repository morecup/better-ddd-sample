import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("com.google.devtools.ksp")
    id("org.springframework.boot")
    java
}

java.sourceCompatibility = JavaVersion.VERSION_1_8

//repositories {
//    mavenLocal()
//    mavenCentral()
////    maven ("https://maven.aliyun.com/repository/public")
//}

dependencies {

    implementation(project(":domain"))

    implementation(libs.jimmer.spring.boot.starter)

    ksp(libs.jimmer.ksp)

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation(libs.spring.boot.starter.web)
    implementation(libs.knife4j.openapi3.spring.boot.starter)
    implementation(libs.swagger.parser)
    implementation(libs.hutool.all)
    implementation(libs.okhttp)
    implementation(libs.gdal)
    implementation(libs.jts.core)
    implementation(libs.commons.io)
    implementation(libs.commons.compress)
    implementation(libs.commons.text)
    implementation("net.coobird:thumbnailator:0.4.14")
    implementation("com.twelvemonkeys.imageio:imageio-tiff:3.9.4")
    implementation("com.twelvemonkeys.imageio:imageio-core:3.9.4")
    implementation("com.github.ben-manes.caffeine:caffeine:2.9.3")
    implementation(libs.hutool.all)

    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.9")

    runtimeOnly("mysql:mysql-connector-java:8.0.30")

    testImplementation(libs.spring.boot.starter.test)

    implementation("io.github.morecup.better-ddd:Jimmer-ddd-kotlin-spring-boot-starter:0.1.2")
}

configurations.all {
    resolutionStrategy {
        // 强制使用指定版本
        force("org.yaml:snakeyaml:1.30")
    }
}



// Without this configuration, gradle command can still run.
// However, Intellij cannot find the generated source.
kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
}

sourceSets{
    main{
        java.setSrcDirs(listOf("src/main/java","src/main/kotlin"))
        kotlin.setSrcDirs(listOf("src/main/java","src/main/kotlin"))
    }
}

ksp {
    arg("jimmer.dto.mutable", "true")
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        freeCompilerArgs.set(listOf("-Xjsr305=strict"))
        jvmTarget.set(JvmTarget.JVM_1_8)
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.getByName<BootJar>("bootJar") {
    archiveFileName.set("jimmer-ddd-kotlin-sample.jar")
}