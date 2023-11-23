package com.github.sfidencio.estudo.inicial.configuration.example1.custom;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) //Somente para classes
@Retention(RetentionPolicy.RUNTIME)
@Configuration
@Profile("production")
public @interface MyConfigurationForDevelopment {
}
