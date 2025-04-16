package br.com.marcos.tarefas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowCredentials(true);

        // Permitir origens específicas dependendo do ambiente
        corsConfig.addAllowedOrigin("http://localhost:8080");  // Local
        corsConfig.addAllowedOrigin("https://www.seu-dominio.com");  // Produção

        corsConfig.addAllowedMethod("*");  // Permitir todos os métodos
        corsConfig.addAllowedHeader("*");  // Permitir todos os cabeçalhos

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsFilter(source);
    }
}
