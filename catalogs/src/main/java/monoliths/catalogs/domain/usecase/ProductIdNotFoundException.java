package monoliths.catalogs.domain.usecase;

import monoliths.commons.SystemException;

import java.util.UUID;

public class ProductIdNotFoundException extends SystemException {

    private final UUID id;

    public ProductIdNotFoundException(UUID id) {
        super("상품 엔티티를 찾을 수 업습니다. (상품일련번호: %s)", id);
        this.id = id;
    }

}
