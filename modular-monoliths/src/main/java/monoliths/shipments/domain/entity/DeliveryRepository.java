package monoliths.shipments.domain.entity;

import java.util.Optional;
import java.util.UUID;

public interface DeliveryRepository {

    Optional<Delivery> findByOrderId(UUID orderId);

    void save(Delivery entity);

}
