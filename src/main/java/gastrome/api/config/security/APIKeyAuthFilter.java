package gastrome.api.config.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

// Autor: Tim Riebesam
// Diese Klasse ist Teil der Security Konfiguration. Sie beinhaltet Methoden, die in der Klasse APISecurityConfig.java verwendet werden um HTTP-Anfragen zu Prüfen.

public class APIKeyAuthFilter extends AbstractPreAuthenticatedProcessingFilter {

    private String principalRequestHeader;

    // Konstruktor um die Variable principalRequestHeader zu implementieren und ein APIKeyAuthFilter-Objekt zu erzeugen
    public APIKeyAuthFilter(String principalRequestHeader) {
        this.principalRequestHeader = principalRequestHeader;
    }

    //Funktionsweise: Extrahiert den Schlüssel mit dem Namen, welcher in der Variable principalRequestHeader gespeichert ist aus einem HttpServletRequest und gibt den Wert dieses Schlüssels zurück
  	//Übergabeparameter: Übergeben wird ein HttpServletRequest namens request
  	//Rückgabewert: Zurückgegeben wird ein Object, bestehend aus dem Wert eines Header-Attributs aus dem Request
    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return request.getHeader(principalRequestHeader);
    }

    //Funktionsweise: Methode die implementiert werden muss da von AbstractPreAuthenticatedProcessingFilter geerbt wird. Methode besitzt keine tiefere Logik. Wurde implementiert da notwendig aber nicht von nutzen, somit Rückgabewert nutzlos
  	//Übergabeparameter: Übergeben wird ein HttpServletRequest namens request
  	//Rückgabewert: Zurückgegeben wird ein Object. In diesem Fall handelt es sich immer um einen String mit dem Wert N/A 
    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return "N/A";
    }

}
