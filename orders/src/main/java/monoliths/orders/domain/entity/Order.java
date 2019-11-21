package monoliths.orders.domain.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import monoliths.commons.model.OrderSheet;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
public class Order {

    private UUID id;
    private UUID customerId;
    private List<OrderProduct> orderProducts = new ArrayList<>();
    private OrderStatus orderStatus;
    private LocalDateTime lastModified;
    private List<OrderStatusHistory> orderStatusHistories = new ArrayList<>();

    private Order() { }

    private Order(UUID customerId, List<OrderProduct> orderProducts) {
        setId(UUID.randomUUID());
        setCustomerId(Objects.requireNonNull(customerId));
        setOrderProducts(Objects.requireNonNull(orderProducts));
    }

    protected Order ordered() {
        return changeOrderStatus(OrderStatus.ORDERED);
    }

    public Order payed(ShippingDesk shippingDesk) {
        shippingDesk.dispatch(this);
        return changeOrderStatus(OrderStatus.PAYED);
    }

    public Order received() {
        return changeOrderStatus(OrderStatus.RECEIVED);
    }

    private Order changeOrderStatus(OrderStatus value) {
        OrderStatusHistory history = new OrderStatusHistory(this, value);
        orderStatusHistories.add(history);

        setOrderStatus(history.getStatus());
        setLastModified(history.getAddedDate());
        return this;
    }

    public BigDecimal calculateTotalPrice() {
        return orderProducts.stream()
                            .map(OrderProduct::calculatePrice)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static Order place(OrderSheet orderSheet, OrderProductMapper orderProductMapper, ShippingDesk shippingDesk) {
        Order order = new Order(orderSheet.getCustomerId(), orderProductMapper.mapFrom(orderSheet.getItems()));
        shippingDesk.register(order, orderSheet);
        return order.ordered();
    }

}
