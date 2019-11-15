package monoliths.catalogs.domain.usecase;

import monoliths.commons.SystemException;

public class SkuCodeNotFoundException extends SystemException {

    private final String code;

    public SkuCodeNotFoundException(String code) {
        super("SKU 엔티티를 찾을 수 업습니다. (코드: %s)", code);
        this.code = code;
    }

}
