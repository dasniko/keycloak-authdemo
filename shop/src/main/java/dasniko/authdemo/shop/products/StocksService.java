package dasniko.authdemo.shop.products;

import dasniko.authdemo.shop.authz.KeycloakClientAuthzRestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
@Component
@RequiredArgsConstructor
public class StocksService {

    private static final String STOCKS_URL = "http://localhost:8082/stocks?productIds=";

    private final KeycloakClientAuthzRestTemplate restTemplate;

    @SuppressWarnings("unchecked")
    public Map<String, Integer> getStocks(List<Product> products) {
        String pids = products.stream()
                .map(Product::getId)
                .collect(Collectors.joining(","));
        ResponseEntity<Map> response = restTemplate.getForEntity(STOCKS_URL + pids, Map.class);
        return response.getBody();
    }

}
