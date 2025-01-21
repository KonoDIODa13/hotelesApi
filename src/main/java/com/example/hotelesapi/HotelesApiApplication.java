package com.example.hotelesapi;

import com.example.hotelesapi.Security.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class HotelesApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelesApiApplication.class, args);
    }

    private static final String[] AUTH_WHITELIST = { //SWAGGER
// -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
// -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/doc/**"
// other public endpoints of your API may be appended to this array
    };
    @EnableWebSecurity
    @Configuration
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/user").permitAll()//antMatchers OBSOLETO
                    .antMatchers("/api/habitacion/espacio/**").permitAll() // Libre acceso a espacio
                    .antMatchers("/api/habitacion/precio/**").permitAll()
                    .antMatchers(AUTH_WHITELIST).permitAll() //SWAGGER
                    .anyRequest().authenticated();//cualquier solicitud debe ser autenticada, de lo contrario, mi aplicación Spring devolverá una respuesta 401.
        }
    }
}

