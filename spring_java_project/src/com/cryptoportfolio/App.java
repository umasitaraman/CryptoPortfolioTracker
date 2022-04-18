package com.cryptoportfolio;

import com.cryptoportfolio.dependency.DaggerServiceComponent;
import com.cryptoportfolio.dependency.ServiceComponent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static final ServiceComponent component = DaggerServiceComponent.create();

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
