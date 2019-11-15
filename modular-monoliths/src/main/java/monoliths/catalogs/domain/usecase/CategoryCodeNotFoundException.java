package monoliths.catalogs.domain.usecase;

import monoliths.commons.SystemException;

public class CategoryCodeNotFoundException extends SystemException {

    private final String code;

    public CategoryCodeNotFoundException(String code) {
        super("카테고리 엔티티를 찾을 수 업습니다. (코드: %s)", code);
        this.code = code;
    }

}
