package com.github.sfidencio.estudo.inicial.configuration.example2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnimalConfiguration {

    @Bean("cachorro")
    public Animal cachorro() {

        //Anonymous class
        /* return new Animal() {
            @Override
            public void fazerBarulho() {
                System.out.println("Au au");
            }
        };*/

        //Lambda expression
        return () -> System.out.println("Au au");
    }


    @Bean("gato")
    public Animal gato() {

        //Anonymous class
        /* return new Animal() {
            @Override
            public void fazerBarulho() {
                System.out.println("Au au");
            }
        };*/

        //Lambda expression
        return () -> System.out.println("Miau");
    }
}
