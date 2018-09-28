package dasniko.authdemo.shop;

import dasniko.authdemo.shop.authz.KeycloakClientAuthzInterceptor;
import dasniko.authdemo.shop.authz.KeycloakClientAuthzRestTemplate;
import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
@RequiredArgsConstructor
@org.springframework.context.annotation.Configuration
public class KeycloakAdapterSecurityConfig {

    private final KeycloakClientRequestFactory keycloakClientRequestFactory;

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public KeycloakRestTemplate keycloakRestTemplate() {
        return new KeycloakRestTemplate(keycloakClientRequestFactory);
    }

//    @Bean
//    @Scope(scopeName = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
//    public KeycloakSecurityContext getKeycloakSecurityContext() {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//        return ((KeycloakPrincipal) request.getUserPrincipal()).getKeycloakSecurityContext();
//    }

    @Bean
    public AuthzClient authzClient(KeycloakSpringBootProperties kcProperties) {
        Configuration configuration = new Configuration(
                kcProperties.getAuthServerUrl(), kcProperties.getRealm(),
                kcProperties.getResource(), kcProperties.getCredentials(), null);
        return AuthzClient.create(configuration);
    }

    @Bean
    @Scope(scopeName = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public KeycloakClientAuthzRestTemplate keycloakClientAuthzRestTemplate(AuthzClient authzClient) {
        String token = authzClient.obtainAccessToken().getToken();
        KeycloakClientAuthzInterceptor keycloakClientAuthzInterceptor = new KeycloakClientAuthzInterceptor(token);
        return new RestTemplateBuilder()
                .additionalInterceptors(keycloakClientAuthzInterceptor)
                .build(KeycloakClientAuthzRestTemplate.class);
    }

}
