package dasniko.authdemo.shop.products;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * @author Niko Köbler, http://www.n-k.de, @dasniko
 */
public class KeycloakClientAuthzInterceptor implements ClientHttpRequestInterceptor {

    private String token;

    public KeycloakClientAuthzInterceptor(String token) {
        this.token = token;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + this.token);
        return execution.execute(request, body);
    }

}
