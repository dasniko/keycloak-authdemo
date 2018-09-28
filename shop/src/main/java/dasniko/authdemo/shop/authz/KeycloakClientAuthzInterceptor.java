package dasniko.authdemo.shop.authz;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
@RequiredArgsConstructor
public class KeycloakClientAuthzInterceptor implements ClientHttpRequestInterceptor {

    private final String token;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + this.token);
        return execution.execute(request, body);
    }

}
