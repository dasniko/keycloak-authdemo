package dasniko.authdemo.shop;

import dasniko.authdemo.shop.cart.Cart;
import dasniko.authdemo.shop.products.Product;
import dasniko.authdemo.shop.products.ProductRepository;
import dasniko.authdemo.shop.products.StocksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
@Controller
@RequiredArgsConstructor
public class ShopController {

    private final Cart cart;
    private final ProductRepository productRepository;
    private final StocksService stocksService;

    @GetMapping("/shop")
    public String shop(Model model) {
        populateModel(model);
        return "shop";
    }

    @PostMapping("/shop")
    public String addToCart(@RequestParam String productId, Model model) {
        cart.addToCart(productRepository.getProduct(productId));
        populateModel(model);
        return "shop";
    }

    private void getStocks(List<Product> products) {
        Map<String, Integer> stocks = stocksService.getStocks(products);
        products.forEach(p -> p.setStock(stocks.get(p.getId())));
    }

    private void populateModel(Model model) {
        List<Product> products = productRepository.getProducts();
        getStocks(products);
        model.addAttribute(products);
        model.addAttribute(cart);
    }

}
