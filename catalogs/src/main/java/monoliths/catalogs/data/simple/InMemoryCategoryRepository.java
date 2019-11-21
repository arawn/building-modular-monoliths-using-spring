package monoliths.catalogs.data.simple;

import monoliths.catalogs.domain.entity.Category;
import monoliths.catalogs.domain.entity.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
class InMemoryCategoryRepository implements CategoryRepository {

    private final Map<UUID, Category> data = new ConcurrentHashMap<>();

    @Override
    public Optional<Category> findByCode(String code) {
        return data.values().stream().filter(it -> Objects.equals(code, it.getCode())).findFirst();
    }

    @Override
    public void save(Category entity) {
        data.putIfAbsent(Objects.requireNonNull(entity).getId(), entity);
        for(Category child : entity.getChildren()) {
            save(child);
        }
    }

}
