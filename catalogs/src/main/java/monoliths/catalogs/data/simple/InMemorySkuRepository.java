package monoliths.catalogs.data.simple;

import monoliths.catalogs.domain.entity.Sku;
import monoliths.catalogs.domain.entity.SkuRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
class InMemorySkuRepository implements SkuRepository {

    private final Map<UUID, Sku> data = new ConcurrentHashMap<>();

    @Override
    public Optional<Sku> findById(UUID id) {
        return data.values().stream().filter(it -> Objects.equals(id, it.getId())).findFirst();
    }

    @Override
    public Optional<Sku> findByCode(String code) {
        return data.values().stream().filter(it -> Objects.equals(code, it.getCode())).findFirst();
    }

    @Override
    public void save(Sku entity) {
        data.putIfAbsent(Objects.requireNonNull(entity).getId(), entity);
    }

}
