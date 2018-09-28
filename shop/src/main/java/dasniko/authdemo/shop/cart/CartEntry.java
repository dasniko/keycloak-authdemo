package dasniko.authdemo.shop.cart;

import dasniko.authdemo.shop.products.Product;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
@Getter
public class CartEntry {
    private Product product;
    private int count;
    private BigDecimal price;

    private CartEntry() {}

    public static CartEntry from(Product product) {
        CartEntry entry = new CartEntry();
        entry.product = product;
        entry.count = 1;
        entry.price = product.getPrice();
        return entry;
    }

    public void increaseCount() {
        this.count++;
        this.price = product.getPrice().multiply(BigDecimal.valueOf(this.count));
    }
}
