package dasniko.authdemo.shop.cart;

import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
@Component
@RequiredArgsConstructor
public class ShippingService {

    private static final String SHIPPING_URL = "http://localhost:8083/shipping";

    private final KeycloakRestTemplate restTemplate;

    public Double calculateShipping() {
        ResponseEntity<Double> response = restTemplate.getForEntity(SHIPPING_URL, Double.class);
        return response.getBody();
    }

}
