package monoliths.orders.domain.entity;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(of = { "id" })
public class OrderProductItem {

    private UUID id;
    private UUID productItemId;
    private String productItemName;
    private BigDecimal productItemPrice;
    private UUID productItemSkuId;

    @Builder
    public OrderProductItem(UUID productItemId, String productItemName, BigDecimal productItemPrice, UUID productItemSkuId) {
        this.id = UUID.randomUUID();
        this.productItemId = productItemId;
        this.productItemName = productItemName;
        this.productItemPrice = productItemPrice;
        this.productItemSkuId = productItemSkuId;
    }

}
