package monoliths.catalogs.domain.entity;

import java.util.Optional;
import java.util.UUID;

public interface SkuRepository {

    Optional<Sku> findById(UUID id);

    Optional<Sku> findByCode(String code);

    void save(Sku entity);

}
