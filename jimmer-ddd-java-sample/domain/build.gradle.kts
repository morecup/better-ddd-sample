
plugins {
    java
}


java.sourceCompatibility = JavaVersion.VERSION_1_8


dependencies {

    implementation(libs.spring.context)
    implementation(libs.swagger.annotations)
    implementation(libs.spring.tx)
    implementation(libs.slf4j.api)
    implementation(libs.jimmer.sql)
    implementation(libs.jimmer.ddd.java)
    implementation(libs.jimmer.spring.boot.starter)
    annotationProcessor(libs.jimmer.apt)
//    必须引入jimmer-spring-boot-starter的依赖，因为Jimmer-ddd-kotlin-spring-boot-starter对jimmer的依赖是编译期的，这样的好处是可以使用自己魔改的jimmer
    implementation(libs.jimmer.ddd.java.spring.boot.starter)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
