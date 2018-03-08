package dasniko.authdemo.shop.cart;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author Niko Köbler, http://www.n-k.de, @dasniko
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ShippingService {

    private static final String SHIPPING_URL = "http://localhost:8083/shipping";

    private final RestTemplate restTemplate;

    public Double calculateShipping() {
        ResponseEntity<Double> response = restTemplate.getForEntity(SHIPPING_URL, Double.class);
        return response.getBody();
    }

}
