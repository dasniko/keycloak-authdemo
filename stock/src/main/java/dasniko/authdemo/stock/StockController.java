package dasniko.authdemo.stock;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Niko Köbler, http://www.n-k.de, @dasniko
 */
@RestController
public class StockController {

    @GetMapping("/stocks")
    public Map<String, Integer> getStocks(@RequestParam("productIds") List<String> productIds) {
        return productIds.stream().collect(Collectors.toMap(pid -> pid, pid -> (int)(Math.random() * 100)));
    }

}
