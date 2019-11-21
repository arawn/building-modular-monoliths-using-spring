package monoliths.shipments.domain.entity.support;

import monoliths.commons.model.OrderSheet;
import monoliths.shipments.domain.entity.Delivery;
import monoliths.shipments.domain.entity.DeliveryFeeCalculator;
import monoliths.shipments.domain.entity.DeliveryValidator;
import monoliths.shipments.domain.entity.DistributionCenter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class StandardReceptionDesk implements DeliveryFeeCalculator, DeliveryValidator {

    private final DistributionCenter distributionCenter;

    public StandardReceptionDesk(DistributionCenter distributionCenter) {
        this.distributionCenter = distributionCenter;
    }

    @Override
    public BigDecimal calculateDeliveryFee(OrderSheet orderSheet) {
        // TODO 배송 품목 및 지역을 바탕으로 운송료 계산

        return BigDecimal.ZERO;
    }

    @Override
    public Delivery validate(Delivery delivery) {
        verifySkuAvailability(delivery);
        verifyDeliveryArea(delivery);
        return delivery;
    }

    private void verifySkuAvailability(Delivery delivery) {
        distributionCenter.verifyStockFor(delivery.getProducts());
    }

    private void verifyDeliveryArea(Delivery delivery) {
        // TODO 배송이 가능한 지역인지 확인한다.
    }

}
