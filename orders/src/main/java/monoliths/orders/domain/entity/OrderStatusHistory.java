package monoliths.orders.domain.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(of = { "id" })
@ToString(of = { "status", "addedDate" })
public class OrderStatusHistory {

    private UUID id;
    private Order order;
    private OrderStatus status;
    private LocalDateTime addedDate;

    private OrderStatusHistory() { }

    protected OrderStatusHistory(Order order, OrderStatus status) {
        this.id = UUID.randomUUID();
        this.order = order;
        this.status = status;
        this.addedDate = LocalDateTime.now();
    }

}
