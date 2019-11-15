package monoliths.shipments.domain.usecase;

import monoliths.commons.model.OrderSheet;

import java.util.UUID;

public interface DeliveryProcessing {

    void prepareDelivery(UUID orderId, OrderSheet orderSheet);

    void dispatchDelivery(UUID orderId);

}
