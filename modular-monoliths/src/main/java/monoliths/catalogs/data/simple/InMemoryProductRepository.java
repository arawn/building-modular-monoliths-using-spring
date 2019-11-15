package monoliths.catalogs.data.simple;

import monoliths.catalogs.domain.entity.Category;
import monoliths.catalogs.domain.entity.Product;
import monoliths.catalogs.domain.entity.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class InMemoryProductRepository implements ProductRepository {

    private final Map<UUID, Product> data = new ConcurrentHashMap<>();

    @Override
    public Optional<Product> findById(UUID id) {
        return Optional.ofNullable(data.getOrDefault(id, null));
    }

    @Override
    public Optional<Product> findByCode(String code) {
        return data.values().stream().filter(it -> Objects.equals(code, it.getCode())).findFirst();
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return data.values().stream().filter(it -> it.getCategories().contains(category)).collect(Collectors.toList());
    }

    @Override
    public void save(Product entity) {
        data.putIfAbsent(Objects.requireNonNull(entity).getId(), entity);
    }

}
