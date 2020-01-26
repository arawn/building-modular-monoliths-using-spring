package monoliths.catalogs.web;

import java.util.Objects;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import monoliths.catalogs.domain.entity.Product;
import monoliths.catalogs.domain.usecase.Catalogs;

@AllArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductRestController {

    private Catalogs catalogs;

    @GetMapping("/find")
    public Product find(FindProductCondition condition) {
        if (condition.hasProductId()) {
            return catalogs.getProduct(condition.getProductId());
        }
        return catalogs.getProductByCode(condition.getProductCode());
    }
    

    @Data
    static class FindProductCondition {

        private UUID productId;
        private String productCode;

        boolean hasProductId() {
            return Objects.nonNull(productId);
        }

    }

}