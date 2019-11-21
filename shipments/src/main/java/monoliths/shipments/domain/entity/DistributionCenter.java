package monoliths.shipments.domain.entity;

import java.util.List;

public interface DistributionCenter {

    void verifyStockFor(List<DeliveryProduct> deliveryProducts);

    void dispatching(Delivery delivery);

}
