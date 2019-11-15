package monoliths.shipments.domain.entity;

public interface DeliveryValidator {

    Delivery validate(Delivery delivery);
    
}
