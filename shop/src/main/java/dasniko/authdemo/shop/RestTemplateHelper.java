package dasniko.authdemo.shop;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;

/**
 * @author Niko Köbler, http://www.n-k.de, @dasniko
 */
@UtilityClass
public class RestTemplateHelper {

    public static HttpHeaders authHeader(String token) {
        return new HttpHeaders() {{
            set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        }};
    }

}
