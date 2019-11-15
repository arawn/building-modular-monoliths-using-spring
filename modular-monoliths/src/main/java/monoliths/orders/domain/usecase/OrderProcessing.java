package monoliths.orders.domain.usecase;

import monoliths.commons.model.OrderSheet;

import java.util.UUID;

public interface OrderProcessing {

    UUID placeOrder(OrderSheet orderSheet);

    void payOrder(UUID orderId);

    void receiveOrder(UUID orderId);

}
