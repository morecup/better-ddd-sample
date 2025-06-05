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


dependencies {

    implementation(project(":domain"))

    ksp(libs.jimmer.ksp)

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation(libs.spring.boot.starter.web)
    implementation(libs.knife4j.openapi3.spring.boot.starter)
    implementation(libs.swagger.parser)
    implementation(libs.hutool.all)
    implementation(libs.commons.io)
    implementation(libs.commons.compress)
    implementation(libs.commons.text)
    implementation(libs.hutool.all)

    runtimeOnly(libs.mysql.connector.java)

    testImplementation(libs.spring.boot.starter.test)

    implementation(libs.jimmer.spring.boot.starter)
//    必须引入jimmer-spring-boot-starter的依赖，因为Jimmer-ddd-kotlin-spring-boot-starter对jimmer的依赖是编译期的，这样的好处是可以使用自己魔改的jimmer
    implementation(libs.jimmer.ddd.kotlin.spring.boot.starter)
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