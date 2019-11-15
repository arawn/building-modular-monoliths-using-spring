package monoliths.shipments.data.simple;

import monoliths.shipments.domain.entity.Delivery;
import monoliths.shipments.domain.entity.DeliveryRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryDeliveryRepository implements DeliveryRepository {

    private final Map<UUID, Delivery> data = new ConcurrentHashMap<>();

    @Override
    public Optional<Delivery> findByOrderId(UUID orderId) {
        return data.values().stream().filter(it -> Objects.equals(orderId, it.getOrderId())).findFirst();
    }

    @Override
    public void save(Delivery entity) {
        data.putIfAbsent(Objects.requireNonNull(entity).getId(), entity);
    }

}
