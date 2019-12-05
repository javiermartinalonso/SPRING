package com.springboot.configuration.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("german")
public class GreetingServiceGerman implements GreetingService {

    @Override
    public String sayHello(String name) {
        return "Halo " + name;
    }
}
