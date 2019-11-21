package monoliths.orders.domain.entity;

import lombok.*;
import monoliths.commons.SystemException;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
public class OrderProduct {

    public static final Long MINIMUM_ORDER_QUANTITY = 1L;

    private UUID id;
    private UUID productId;
    private String productName;
    private List<OrderProductItem> productItems;

    private BigDecimal price;
    private long quantity;

    @Builder
    public OrderProduct(UUID productId, String productName, List<OrderProductItem> productItems, BigDecimal price, long quantity) {
        setId(UUID.randomUUID());
        setProductId(productId);
        setProductName(productName);
        setProductItems(productItems);
        setPrice(price);
        setQuantity(quantity);
    }

    public void setQuantity(long quantity) {
        if (quantity < MINIMUM_ORDER_QUANTITY) {
            throw new IncorrectOrderProductQuantityException(quantity);
        }
        this.quantity = quantity;
    }

    public BigDecimal calculatePrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    public static class IncorrectOrderProductQuantityException extends SystemException {

        private final Long quantity;

        public IncorrectOrderProductQuantityException(long quantity) {
            super("주문 수량이 잘못되었습니다. (수량: %d)", quantity);
            this.quantity = quantity;
        }

    }

}