package monoliths.orders.domain.usecase;

import lombok.AllArgsConstructor;
import monoliths.commons.SystemException;
import monoliths.orders.domain.entity.Order;
import monoliths.orders.domain.entity.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@AllArgsConstructor
@Component
class OrderFinder implements Orders {

    private OrderRepository orderRepository;

    @Override
    public Order getOrder(UUID orderId) {
        return orderRepository.findById(orderId)
                              .orElseThrow(() -> new OrderIdNotFoundException(orderId));
    }

    Order store(Order order) {
        orderRepository.save(order);
        return order;
    }

    static class OrderIdNotFoundException extends SystemException {

        private final UUID orderId;

        public OrderIdNotFoundException(UUID orderId) {
            super("주문 엔티티를 찾을 수 업습니다. (주문일련번호: %s)", orderId);
            this.orderId = orderId;
        }

    }

}