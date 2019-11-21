package monoliths.orders.data.simple;

import monoliths.orders.domain.entity.Order;
import monoliths.orders.domain.entity.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
class InMemoryOrderRepository implements OrderRepository {

    private final Map<UUID, Order> data = new ConcurrentHashMap<>();

    @Override
    public List<Order> findByCustomerId(UUID customerId) {
        return data.values().stream().filter(it -> Objects.equals(customerId, it.getCustomerId())).collect(Collectors.toList());
    }

    @Override
    public Optional<Order> findById(UUID id) {
        return data.values().stream().filter(it -> Objects.equals(id, it.getId())).findFirst();
    }

    @Override
    public void save(Order entity) {
        data.putIfAbsent(Objects.requireNonNull(entity).getId(), entity);
    }

}
