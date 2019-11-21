package monoliths.orders.domain.entity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {

    List<Order> findByCustomerId(UUID customerId);

    Optional<Order> findById(UUID id);

    void save(Order entity);

}
