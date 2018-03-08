package dasniko.authdemo.shop.products;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Niko Köbler, http://www.n-k.de, @dasniko
 */
@Component
@RequiredArgsConstructor
public class ProductRepository {

    private static final List<Product> products = new ArrayList<>();

    static {
        products.add(new Product("1", "Cat", 11.11));
        products.add(new Product("2", "Cute Cat", 22.22));
        products.add(new Product("3", "Nice Cat", 33.33));
        products.add(new Product("4", "Tiny Cat", 44.44));
        products.add(new Product("5", "Lovely Cat", 55.55));
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProduct(String id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().get();
    }

}
