package org.morecup.jimmerddd.kotlin.sample.admin

import org.babyfish.jimmer.client.EnableImplicitApi
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableImplicitApi
@SpringBootApplication(scanBasePackages = ["org.morecup.jimmerddd.kotlin.sample"])
class App {
}

fun main(args: Array<String>) {
    runApplication<App>(*args)
}