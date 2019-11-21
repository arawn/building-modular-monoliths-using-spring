package monoliths.shipments.domain.usecase;

import lombok.AllArgsConstructor;
import monoliths.commons.SystemException;
import monoliths.shipments.domain.entity.Delivery;
import monoliths.shipments.domain.entity.DeliveryRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@AllArgsConstructor
@Component
class DeliveryFinder implements Deliveries {

    private DeliveryRepository deliveryRepository;

    Delivery getDeliveryByOrderId(UUID orderId) {
        return deliveryRepository.findByOrderId(orderId)
                                 .orElseThrow(() -> new OrderIdNotFoundException(orderId));
    }

    void store(Delivery delivery) {
        deliveryRepository.save(delivery);
    }

    @Override
    public Delivery getDelivery(UUID orderId) {
        return getDeliveryByOrderId(orderId);
    }

    static class OrderIdNotFoundException extends SystemException {

        private final UUID orderId;

        public OrderIdNotFoundException(UUID orderId) {
            super("주문 엔티티를 찾을 수 업습니다. (주문일련번호: %s)", orderId);
            this.orderId = orderId;
        }

    }

}