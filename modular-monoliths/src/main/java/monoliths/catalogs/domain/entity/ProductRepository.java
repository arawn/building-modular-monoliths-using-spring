package monoliths.catalogs.domain.entity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {

    Optional<Product> findById(UUID id);

    Optional<Product> findByCode(String code);

    List<Product> findByCategory(Category category);

    void save(Product entity);

}
