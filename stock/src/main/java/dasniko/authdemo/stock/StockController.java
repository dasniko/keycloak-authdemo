package dasniko.authdemo.stock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Niko Köbler, http://www.n-k.de, @dasniko
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class StockController {

    @GetMapping("/stocks")
    public Map<String, Integer> getStocks(@RequestParam("productIds") List<String> productIds, HttpServletRequest request) {
        KeycloakSecurityContext context = (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
        System.out.println(context.getTokenString());
        return productIds.stream()
                .collect(Collectors.toMap(pid -> pid, pid -> (int)(Math.random() * 100)));
    }

}
