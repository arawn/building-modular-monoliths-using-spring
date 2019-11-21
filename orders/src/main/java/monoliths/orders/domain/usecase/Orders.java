package monoliths.orders.domain.usecase;

import monoliths.orders.domain.entity.Order;

import java.util.UUID;

public interface Orders {

    Order getOrder(UUID orderId);

}
