package gastrome.api.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

// Autor Tim Riebesam
// Diese Klasse ist Teil der Security Konfiguration. Sie beinhaltet eine Methode um die Security-Richtlinien der Anwendung zu Konfigurieren

@Configuration
@EnableWebSecurity
@Order(1)
public class APISecurityConfig extends WebSecurityConfigurerAdapter {

	// Übernehmen des Wertes des Header-Namens und dazugehöriger Value aus der Datei src/main/resources/application.properties und speichere in lokalen Java-Variablen
    @Value("${gastrome.config.http.auth-token-header-name}")
    private String principalRequestHeader;

    @Value("${gastrome.config.http.auth-token}")
    private String principalRequestValue;

    // Diese Methode wird von WebSecurityConfigurerAdapter geerbt und beinhaltet die Security-Konfiguration der Anwendung.
    // Funktionsweise: Es wird ein neuer APIKeyAuthFilter generiert. Anschließend wird dem filter ein AuthenticationManager hinzugeüfgt, über welchen jeder HTTP-Request geprüft wird. 
    //  Eine Anfrage wird akzeptiert wenn der Wert des Header-Attributs mit dem Namen, welcher in der Variable principalRequestHeader gespeichert ist, dem Wert der Variable principalRequestValue entspricht.
    //  Die Werte dieser Variablen sind wie oben beschrieben in der Datei "application.properties" zu finden.
    //  Sind die Werte nicht identisch, wird eine BadCredentialsException geworfen (entspricht HTTP-Error-Code 403) mit der Information "The API key was not found or not the expected value."
  	// Übergabeparameter: Übergeben wird ein HttpSecurity-Objekt namens httpSecurity
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        APIKeyAuthFilter filter = new APIKeyAuthFilter(principalRequestHeader);
        filter.setAuthenticationManager(new AuthenticationManager() {

            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                String principal = (String) authentication.getPrincipal();
                if (!principalRequestValue.equals(principal))
                {
                    throw new BadCredentialsException("The API key was not found or not the expected value.");
                }
                authentication.setAuthenticated(true);
                return authentication;
            }
        });
        httpSecurity.
            antMatcher("/**").
            csrf().disable().
            sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
            and().cors().
            and().addFilter(filter).authorizeRequests().anyRequest().authenticated();
    }

}
