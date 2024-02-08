package br.com.sfidencio.clientes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    //https://stackoverflow.com/questions/37671125/how-to-configure-spring-security-to-allow-swagger-url-to-be-accessed-without-aut
    private final String[] URLS_SWAGGER = {"/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"};

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests()
                //.antMatchers("/api/v1/clientes/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/api/v1/clientes/**").authenticated()
                .antMatchers("/api/v1/prestacao-servicos/**").authenticated()
                .antMatchers("/api/v1/contatos/**").authenticated()
                .antMatchers("/api/v1/usuarios/**").permitAll()
                .antMatchers("/oauth/token/**", "/h2-console/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(URLS_SWAGGER).permitAll()
                .anyRequest().denyAll();

        http.headers().frameOptions().sameOrigin();
    }

}
