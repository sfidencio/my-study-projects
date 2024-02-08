package br.com.sfidencio.minhaprimeiraapirestfull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.*;

@Configuration
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Autowired
@Qualifier("gato")
public @interface Gato {
}
