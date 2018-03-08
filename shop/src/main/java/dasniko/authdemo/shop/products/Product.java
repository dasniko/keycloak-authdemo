package dasniko.authdemo.shop.products;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Niko Köbler, http://www.n-k.de, @dasniko
 */
@ToString
@RequiredArgsConstructor
public class Product {
    @Getter
    private final String id;
    @Getter
    private final String title;
    @Getter
    private final double price;
    @Getter
    @Setter
    private int stock;
}
