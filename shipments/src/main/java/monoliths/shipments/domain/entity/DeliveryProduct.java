package monoliths.shipments.domain.entity;

import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
public class DeliveryProduct {

    private UUID id;
    private UUID productId;
    private List<DeliveryProductItem> productItems;
    private long quantity;

    @Builder
    public DeliveryProduct(UUID productId, List<DeliveryProductItem> productItems, long quantity) {
        setId(UUID.randomUUID());
        setProductId(Objects.requireNonNull(productId));
        setProductItems(Objects.requireNonNull(productItems));
        setQuantity(quantity);
    }

}