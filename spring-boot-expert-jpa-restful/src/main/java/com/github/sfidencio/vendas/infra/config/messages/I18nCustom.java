package com.github.sfidencio.vendas.infra.config.messages;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.nio.charset.StandardCharsets;

@Configuration
@Log4j2
public class I18nCustom {
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:ValidationMessages"); //Utiliza o arquivo ValidationMessages.properties
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        //messageSource.setDefaultEncoding(StandardCharsets.ISO_8859_1.name());
        messageSource.setDefaultLocale(java.util.Locale.getDefault());
        //log.info(messageSource.getMessage("cliente.nome.obrigatorio", null, java.util.Locale.getDefault()));
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }
}
