package monoliths.catalogs.domain.entity;

import monoliths.commons.SystemException;

import java.util.UUID;

public class InsufficientStockException extends SystemException {

    private final UUID skuId;

    public InsufficientStockException(UUID skuId) {
        super("재고가 불충분합니다. (SKU 일련번호: %s)", skuId);
        this.skuId = skuId;
    }

}
