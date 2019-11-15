package monoliths.catalogs;

import monoliths.catalogs.domain.entity.*;
import org.springframework.beans.factory.InitializingBean;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class CatalogFixtures implements InitializingBean {

    public static final String SIMPLE_PRODUCT_CODE = "SCPOS";

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private SkuRepository skuRepository;

    public CatalogFixtures(CategoryRepository categoryRepository, ProductRepository productRepository, SkuRepository skuRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.skuRepository = skuRepository;
    }

    public void initialize() {
        List<Sku> skus = Arrays.asList(Sku.registry("SCPPO", "포카칩 오리지날"),
                Sku.registry("SCPPN", "포카칩 어니언"),
                Sku.registry("SCPSH", "스윙칩 볶음고추장"),
                Sku.registry("SCPSS", "스윙칩 간장치킨맛"));
        skus.forEach(sku -> sku.refillBy(100));
        skus.forEach(skuRepository::save);

        Category categories = Category.create("SNACK", "간식");
        Category chipsCategory = categories.child("CHIPS", "칩"); {
            chipsCategory.child("POTATO", "감자");
            chipsCategory.child("SHRIMP", "새우");
        }
        categoryRepository.save(categories);

        Product product = Product.registry("SCPOS", "오리온 생감자팩", ProductItem.base(skus.get(0), BigDecimal.valueOf(1200)), new HashSet<>(Arrays.asList(ProductItem.additive(skus.get(1), BigDecimal.valueOf(1200)), ProductItem.additive(skus.get(2), BigDecimal.valueOf(1500)), ProductItem.additive(skus.get(3), BigDecimal.valueOf(1500))))); {
            product.link(categoryRepository.findByCode("SNACK").orElseThrow());
            product.link(categoryRepository.findByCode("CHIPS").orElseThrow());
            product.link(categoryRepository.findByCode("POTATO").orElseThrow());
        }
        productRepository.save(product);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initialize();
    }

}