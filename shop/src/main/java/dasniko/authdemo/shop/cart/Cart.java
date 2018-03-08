package dasniko.authdemo.shop.cart;

import dasniko.authdemo.shop.products.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Niko Köbler, http://www.n-k.de, @dasniko
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class Cart {

    private final ShippingService shippingService;

    @Getter
    private List<CartEntry> entries = new ArrayList<>();
    private double shipping;

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
        return entries.stream().mapToDouble(CartEntry::getPrice).sum();
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
