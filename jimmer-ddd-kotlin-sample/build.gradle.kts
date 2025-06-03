group = "org.morecup.better-ddd.sample"
version = "0.0.1"

plugins {
    kotlin("jvm") version "2.1.0" apply false
    kotlin("plugin.spring") version "2.1.0" apply false
    id("com.google.devtools.ksp") version "2.1.0-1.0.29" apply false
    id("org.springframework.boot") version "2.7.18" apply false
    java
}