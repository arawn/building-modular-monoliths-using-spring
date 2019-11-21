package monoliths.catalogs.domain.usecase;

import monoliths.catalogs.domain.entity.Category;
import monoliths.catalogs.domain.entity.Product;

import java.util.List;
import java.util.UUID;

public interface Catalogs {

    Category getCategoryByCode(String categoryCode);

    Product getProduct(UUID productId);

    Product getProductByCode(String code);

    List<Product> getProductsByCategoryCode(String categoryCode);

}
