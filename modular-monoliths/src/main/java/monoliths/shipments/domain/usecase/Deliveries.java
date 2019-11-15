package monoliths.shipments.domain.usecase;

import monoliths.shipments.domain.entity.Delivery;

import java.util.UUID;

public interface Deliveries {

    Delivery getDelivery(UUID orderId);

}
