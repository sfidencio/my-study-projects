package com.github.sfidencio.estudo.inicial.configuration.example2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) //Somente para atribtuos
@Retention(RetentionPolicy.RUNTIME)
@Autowired
@Qualifier("cachorro")
public @interface Cachorro {
}
