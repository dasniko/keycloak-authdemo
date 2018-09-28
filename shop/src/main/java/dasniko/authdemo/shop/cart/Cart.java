package dasniko.authdemo.shop.cart;

import dasniko.authdemo.shop.products.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
@Component
@RequiredArgsConstructor
@Scope(scopeName = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {

    private final ShippingService shippingService;

    @Getter
    private List<CartEntry> entries = new ArrayList<>();

    public void addToCart(Product product) {
        Optional<CartEntry> entry = findEntry(product.getId());
        if (entry.isPresent()) {
            CartEntry cartEntry = entry.get();
            cartEntry.increaseCount();
        } else {
            entries.add(CartEntry.from(product));
        }
    }

    public double getSum() {
        return entries.stream().mapToDouble(e -> e.getPrice().doubleValue()).sum();
    }

    public double getShipping() {
        if (entries.isEmpty()) {
            return 0;
        } else {
            return shippingService.calculateShipping();
        }
    }

    public double getTotal() {
        return getSum() + getShipping();
    }

    private Optional<CartEntry> findEntry(String productId) {
        return entries.stream().filter(e -> e.getProduct().getId().equals(productId)).findFirst();
    }

}
