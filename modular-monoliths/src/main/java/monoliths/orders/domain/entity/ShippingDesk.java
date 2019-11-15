package monoliths.orders.domain.entity;

import monoliths.commons.model.OrderSheet;

public interface ShippingDesk {

    void register(Order order, OrderSheet orderSheet);

    void dispatch(Order order);

}