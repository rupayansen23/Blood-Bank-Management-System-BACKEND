package com.minorProject.bloodBank.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())                                // ✅ new CSRF style
//                .authorizeHttpRequests(auth -> auth                          // ✅ lambda DSL
//                        .requestMatchers("/saveAdmin", "/adminLogin", "/saveHospital", "/saveBloodBank").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/getBloodBankById/{id}")// allow login
//                )
//                .formLogin(form -> form.disable())
//                .httpBasic(basic -> basic.disable());
//
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Allow cross-origin calls (safe default; adjust with a CorsConfigurationSource if needed)
                .cors(Customizer.withDefaults())
                // Disable CSRF for stateless REST endpoints
                .csrf(csrf -> csrf.disable())
                // No auth required anywhere
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                // Turn off login mechanisms so Security doesn't challenge
                .httpBasic(org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer::disable)
                .formLogin(org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer::disable);

        return http.build();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
