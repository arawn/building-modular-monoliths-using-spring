package monoliths.orders.domain.usecase;

import lombok.AllArgsConstructor;
import monoliths.commons.model.OrderSheet;
import monoliths.orders.domain.entity.Order;
import monoliths.orders.domain.entity.OrderProductMapper;
import monoliths.orders.domain.entity.ShippingDesk;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class OrderManager implements OrderProcessing {

    private OrderProductMapper orderProductMapper;
    private ShippingDesk shippingDesk;
    private OrderFinder orders;

    @Override
    public UUID placeOrder(OrderSheet orderSheet) {
        Order placedOrder = Order.place(orderSheet, orderProductMapper, shippingDesk);

        return orders.store(placedOrder).getId();
    }

    @Override
    public void payOrder(UUID orderId) {
        Order payedOrder = orders.getOrder(orderId).payed(shippingDesk);

        orders.store(payedOrder);
    }

    @Override
    public void receiveOrder(UUID orderId) {
        Order deliveredOrder = orders.getOrder(orderId).received();

        orders.store(deliveredOrder);
    }

}
