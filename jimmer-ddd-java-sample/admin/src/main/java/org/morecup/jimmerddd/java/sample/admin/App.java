package org.morecup.jimmerddd.java.sample.admin;

import org.babyfish.jimmer.client.EnableImplicitApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableImplicitApi
@SpringBootApplication(scanBasePackages = "org.morecup.jimmerddd.java.sample")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}