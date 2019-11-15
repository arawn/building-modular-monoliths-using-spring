package monoliths.catalogs.domain.usecase;

import monoliths.commons.SystemException;

public class ProductCodeNotFoundException extends SystemException {

    private final String code;

    public ProductCodeNotFoundException(String code) {
        super("상품 엔티티를 찾을 수 업습니다. (코드: %s)", code);
        this.code = code;
    }

}
