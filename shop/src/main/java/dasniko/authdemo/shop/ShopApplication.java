package dasniko.authdemo.shop;

import dasniko.authdemo.shop.products.KeycloakClientAuthzInterceptor;
import dasniko.authdemo.shop.products.KeycloakClientAuthzRestTemplate;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.adapters.springboot.client.KeycloakRestTemplateCustomizer;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

    @Bean
    @Scope(scopeName = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public KeycloakSecurityContext getKeycloakSecurityContext() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return ((KeycloakPrincipal) request.getUserPrincipal()).getKeycloakSecurityContext();
    }

    @Bean
    public AuthzClient authzClient(KeycloakSpringBootProperties kcProperties) {
        Configuration configuration = new Configuration(
                kcProperties.getAuthServerUrl(), kcProperties.getRealm(),
                kcProperties.getResource(), kcProperties.getCredentials(), null);
        return AuthzClient.create(configuration);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        KeycloakRestTemplateCustomizer keycloakRestTemplateCustomizer = new KeycloakRestTemplateCustomizer();
        keycloakRestTemplateCustomizer.customize(restTemplate);
        return restTemplate;
    }

    @Bean
    public KeycloakClientAuthzRestTemplate keycloakClientAuthzRestTemplate(AuthzClient authzClient) {
        String token = authzClient.obtainAccessToken().getToken();
        KeycloakClientAuthzInterceptor keycloakClientAuthzInterceptor = new KeycloakClientAuthzInterceptor(token);
        return new RestTemplateBuilder()
                .additionalInterceptors(keycloakClientAuthzInterceptor)
                .build(KeycloakClientAuthzRestTemplate.class);
    }

}
