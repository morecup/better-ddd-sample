import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("com.google.devtools.ksp")
}


java.sourceCompatibility = JavaVersion.VERSION_1_8


dependencies {
    implementation(libs.jimmer.sql.kotlin)
    ksp(libs.jimmer.ksp)

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation(libs.spring.context)
    implementation(libs.swagger.annotations)
    implementation(libs.spring.tx)
    implementation("org.slf4j:slf4j-api:1.7.36")

    implementation("io.github.morecup.better-ddd:Jimmer-ddd-kotlin-spring-boot-starter:0.1.2")
}

// Without this configuration, gradle command can still run.
// However, Intellij cannot find the generated source.
kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
}

ksp {
    arg("jimmer.dto.mutable", "true")
    arg("jimmer.client.saveAllClassDocuments", "true")
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
