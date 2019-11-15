package monoliths.shipments.domain.usecase;

import lombok.AllArgsConstructor;
import monoliths.commons.model.OrderSheet;
import monoliths.shipments.domain.entity.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class DeliveryManager implements DeliveryProcessing {

    private DeliveryProductMapper deliveryProductMapper;
    private DeliveryFeeCalculator deliveryFeeCalculator;
    private DeliveryValidator deliveryValidator;
    private DistributionCenter distributionCenter;
    private DeliveryFinder deliveries;

    @Override
    public void prepareDelivery(UUID orderId, OrderSheet orderSheet) {
        Delivery preparedDelivery = Delivery.prepare(orderId, orderSheet, deliveryProductMapper, deliveryFeeCalculator, deliveryValidator);

        deliveries.store(preparedDelivery);
    }

    @Override
    public void dispatchDelivery(UUID orderId) {
        Delivery dispatchedDelivery = deliveries.getDeliveryByOrderId(orderId).dispatched(distributionCenter);

        deliveries.store(dispatchedDelivery);
    }

}
