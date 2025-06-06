
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
    annotationProcessor(libs.jimmer.apt)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
