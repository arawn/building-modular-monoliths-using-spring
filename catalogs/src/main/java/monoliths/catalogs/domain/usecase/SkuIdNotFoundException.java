package monoliths.catalogs.domain.usecase;

import monoliths.commons.SystemException;

import java.util.UUID;

public class SkuIdNotFoundException extends SystemException {

    private final UUID id;

    public SkuIdNotFoundException(UUID id) {
        super("SKU 엔티티를 찾을 수 업습니다. (일련번호: %s)", id);
        this.id = id;
    }

}
