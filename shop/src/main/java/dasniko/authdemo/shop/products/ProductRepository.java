package dasniko.authdemo.shop.products;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
@Component
public class ProductRepository {

    private static final List<Product> products = new ArrayList<>();

    static {
        products.add(new Product("1", "Cat", new BigDecimal("11.11")));
        products.add(new Product("2", "Grumpy Cat", new BigDecimal("22.22")));
        products.add(new Product("3", "Garfield", new BigDecimal("33.33")));
        products.add(new Product("4", "Tomcat", new BigDecimal("44.44")));
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProduct(String id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().get();
    }

}
