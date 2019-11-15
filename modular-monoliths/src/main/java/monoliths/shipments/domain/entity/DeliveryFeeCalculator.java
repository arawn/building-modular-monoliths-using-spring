package monoliths.shipments.domain.entity;

import monoliths.commons.model.OrderSheet;

import java.math.BigDecimal;

public interface DeliveryFeeCalculator {

    BigDecimal calculateDeliveryFee(OrderSheet orderSheet);
    
}
