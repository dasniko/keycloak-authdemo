package dasniko.authdemo.shop.products;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
@Getter
@ToString
@RequiredArgsConstructor
public class Product {

    private final String id;
    private final String title;
    private final BigDecimal price;
    @Setter
    private int stock;
}
