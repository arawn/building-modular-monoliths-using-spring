package monoliths.catalogs;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.InitializingBean;

import monoliths.catalogs.domain.entity.Category;
import monoliths.catalogs.domain.entity.CategoryRepository;
import monoliths.catalogs.domain.entity.Product;
import monoliths.catalogs.domain.entity.ProductItem;
import monoliths.catalogs.domain.entity.ProductRepository;
import monoliths.catalogs.domain.entity.Sku;
import monoliths.catalogs.domain.entity.SkuRepository;

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
        List<Sku> skus = skus();
        skus.forEach(skuRepository::save);

        Category categories = categories();
        categoryRepository.save(categories);

        Product product = product();
        productRepository.save(product);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initialize();
    }

    public static final List<Sku> skus() {
        List<Sku> skus = Arrays.asList(Sku.registry("SCPPO", "포카칩 오리지날"),
        Sku.registry("SCPPN", "포카칩 어니언"),
        Sku.registry("SCPSH", "스윙칩 볶음고추장"),
        Sku.registry("SCPSS", "스윙칩 간장치킨맛"));
        skus.forEach(sku -> sku.refillBy(100));

        return skus;
    }

    public static final Category categories() {
        Category categories = Category.create("SNACK", "간식");
        Category chipsCategory = categories.child("CHIPS", "칩"); {
            chipsCategory.child("POTATO", "감자");
            chipsCategory.child("SHRIMP", "새우");
        }

        return categories;
    }

    private static final Category SNACK() {
        return Objects.requireNonNull(categories());
    }

    private static final Category CHIPS() {
        return Objects.requireNonNull(categories().getChildren().get(0));
    }

    private static final Category POTATO() {
        return Objects.requireNonNull(CHIPS().getChildren().get(0));
    }

    private static final Category SHRIMP() {
        return Objects.requireNonNull(CHIPS().getChildren().get(1));
    }

    public static final Product product() {
        List<Sku> skus = skus();
        Product product = Product.registry("SCPOS", "오리온 생감자팩", ProductItem.base(skus.get(0), BigDecimal.valueOf(1200)), new HashSet<>(Arrays.asList(ProductItem.additive(skus.get(1), BigDecimal.valueOf(1200)), ProductItem.additive(skus.get(2), BigDecimal.valueOf(1500)), ProductItem.additive(skus.get(3), BigDecimal.valueOf(1500))))); {
            product.link(SNACK());
            product.link(CHIPS());
            product.link(POTATO());
        };

        return product;
    }

}