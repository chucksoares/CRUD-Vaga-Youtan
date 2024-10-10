package security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;


@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Permitir todas as requisições
                )
                .csrf(AbstractHttpConfigurer::disable)  // Desabilitar CSRF para testes
                .formLogin(AbstractHttpConfigurer::disable) // Desabilitar login de formulário
                .httpBasic(AbstractHttpConfigurer::disable); // Desabilitar autenticação básica
        return http.build();
    }
}