package monoliths.catalogs.domain.usecase;

import lombok.AllArgsConstructor;
import lombok.val;
import monoliths.catalogs.domain.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@Service
class CatalogManager implements Catalogs, Inventory {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private SkuRepository skuRepository;

    @Override
    public Category getCategoryByCode(String categoryCode) {
        return categoryRepository.findByCode(categoryCode)
                                 .orElseThrow(() -> new CategoryCodeNotFoundException(categoryCode));
    }

    @Override
    public Product getProduct(UUID productId) {
        return productRepository.findById(productId)
                                .orElseThrow(() -> new ProductIdNotFoundException(productId));
    }

    @Override
    public List<Product> getProductsByCategoryCode(String categoryCode) {
        val category = categoryRepository.findByCode(categoryCode)
                                         .orElseThrow(() -> new CategoryCodeNotFoundException(categoryCode));
        return productRepository.findByCategory(category);
    }

    @Override
    public Product getProductByCode(String code) {
        return productRepository.findByCode(code)
                                .orElseThrow(() -> new ProductCodeNotFoundException(code));
    }

    @Override
    public Sku getSku(UUID skuId) {
        return getSkuById(skuId);
    }

    @Override
    public long getStockFor(Sku sku) {
        return getSkuById(sku.getId()).getStockAmount();
    }

    @Override
    public void verifyStockAvailabilityFor(Sku sku, long amount) {
        getSkuById(sku.getId()).verifyAvailability(amount);
    }

    @Override
    public void incomingFor(Sku sku, long stockAmount) {
        val entity = skuRepository.findById(sku.getId()).orElse(sku); {
            entity.refillBy(stockAmount);
        }
        skuRepository.save(entity);
    }

    @Override
    public void outgoingFor(Sku sku, long stockAmount) {
        val entity = getSkuById(Objects.requireNonNull(sku).getId()); {
            entity.reduceStockBy(stockAmount);
        }
        skuRepository.save(entity);
    }

    private Sku getSkuById(UUID skuId) {
        return skuRepository.findById(skuId)
                            .orElseThrow(() -> new SkuIdNotFoundException(skuId));
    }

}
