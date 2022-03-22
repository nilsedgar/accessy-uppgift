package se.nilsedgar.accessy.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Disable authorization checks for all requests
                .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
                // Disable CSRF protection
                .csrf(csrf -> csrf.disable())
                // Disable CORS policy checks
                .cors(cors -> cors.disable());
    }
}
