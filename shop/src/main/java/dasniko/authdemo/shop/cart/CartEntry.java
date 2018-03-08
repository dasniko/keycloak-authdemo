package dasniko.authdemo.shop.cart;

import dasniko.authdemo.shop.products.Product;
import lombok.Getter;

/**
 * @author Niko Köbler, http://www.n-k.de, @dasniko
 */
@Getter
public class CartEntry {
    private Product product;
    private int count;
    private double price;

    private CartEntry() {}

    public static CartEntry from(Product product) {
        CartEntry entry = new CartEntry();
        entry.product = product;
        entry.count = 1;
        entry.price = product.getPrice() * entry.count;
        return entry;
    }

    public void increaseCount() {
        this.count++;
        this.price = this.count * product.getPrice();
    }
}
