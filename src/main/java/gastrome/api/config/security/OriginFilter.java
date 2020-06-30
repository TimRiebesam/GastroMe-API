package gastrome.api.config.security;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

// Autor: Tim Riebesam
// Diese Klasse ist Teil der Security Konfiguration. Sie beinhaltet einen CorsFilter (Cross-Origin Resource Sharing, insbesondere für das HTML/JS-Dashbaord notwendig, da andernfalls Anfragen scheitern. 
//  Für mehr Informationen: https://javascript.info/fetch-crossorigin). 

@Configuration
public class OriginFilter {
	
	@Value("${gastrome.config.http.auth-token-header-name}")
    private String principalRequestHeader;

	@Bean
	public CorsFilter corsFilter() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    final CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true);
	    config.setAllowedOrigins(Collections.singletonList("*"));
	    config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", principalRequestHeader));
	    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
	    source.registerCorsConfiguration("/**", config);
	    return new CorsFilter(source);
	}
	
}
