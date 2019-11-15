package monoliths.catalogs.domain.entity;

import java.util.Optional;

public interface CategoryRepository {

    Optional<Category> findByCode(String code);

    void save(Category entity);

}