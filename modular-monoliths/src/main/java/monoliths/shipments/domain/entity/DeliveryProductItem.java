package monoliths.shipments.domain.entity;

import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Data
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
public class DeliveryProductItem {

    private UUID id;
    private UUID productItemId;
    private UUID productItemSkuId;

    @Builder
    public DeliveryProductItem(UUID productItemId, UUID productItemSkuId) {
        setId(UUID.randomUUID());
        setProductItemId(Objects.requireNonNull(productItemId));
        setProductItemSkuId(Objects.requireNonNull(productItemSkuId));
    }

}
