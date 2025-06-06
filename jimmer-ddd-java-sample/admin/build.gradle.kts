import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")
    java
}

java.sourceCompatibility = JavaVersion.VERSION_1_8


dependencies {
    annotationProcessor(libs.jimmer.apt)
    implementation(project(":domain"))

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
    implementation(libs.jimmer.ddd.java.spring.boot.starter)
}

configurations.all {
    resolutionStrategy {
        // 强制使用指定版本
        force("org.yaml:snakeyaml:1.30")
    }
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-Ajimmer.client.ignoreJdkWarning=true") // 可选：忽略警告
}

sourceSets{
    main{
        java.setSrcDirs(listOf("src/main/java"))
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.getByName<BootJar>("bootJar") {
    archiveFileName.set("jimmer-ddd-java-sample.jar")
}