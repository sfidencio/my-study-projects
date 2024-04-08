package com.github.sfidencio.demo.infra.config;

import com.github.sfidencio.demo.infra.myprovider.imp01.MyProvider;
import dev.openfeature.sdk.OpenFeatureAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenFeatureConfig {
    @Bean
    public OpenFeatureAPI openFeatureAPI() {
        final OpenFeatureAPI openFeatureAPI = OpenFeatureAPI.getInstance();
        openFeatureAPI.setProviderAndWait(new MyProvider());
        return openFeatureAPI;
    }
}
