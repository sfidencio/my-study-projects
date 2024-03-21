package com.github.sfidencio.exemplo;

import com.github.sfidencio.exemplo.domain.validators.ValidProduct;
import com.github.sfidencio.exemplo.domain.validators.imp.CheckIfStockInsufficient;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.List;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ExemploAopAnnotationsJunit5Application {

    public static void main(String[] args) {
        SpringApplication.run(ExemploAopAnnotationsJunit5Application.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean(name = "validProducts")
    public List<ValidProduct> validProducts() {
        return List.of(new CheckIfStockInsufficient());
    }

}
