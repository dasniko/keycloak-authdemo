package dasniko.authdemo.shipping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Niko Köbler, http://www.n-k.de, @dasniko
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class ShippingController {

    @GetMapping("shipping")
    public Double getShipping(HttpServletRequest request) {
        KeycloakSecurityContext context = (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
        Map<String, Object> claims = context.getToken().getOtherClaims();
        Boolean vip = (Boolean) claims.get("vip");
        if (null != vip && vip) {
            return 0.00;
        } else {
            return 9.99;
        }
    }

}
