package monoliths.shipments.integrate;

import lombok.AllArgsConstructor;
import monoliths.catalogs.domain.entity.Sku;
import monoliths.catalogs.domain.usecase.Inventory;
import monoliths.shipments.domain.entity.Delivery;
import monoliths.shipments.domain.entity.DeliveryProduct;
import monoliths.shipments.domain.entity.DeliveryProductItem;
import monoliths.shipments.domain.entity.DistributionCenter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
@Component
public class FulfillmentService implements DistributionCenter {

    private Inventory inventory;

    @Override
    public void verifyStockFor(List<DeliveryProduct> deliveryProducts) {
        mergedDeliveryQuantities(deliveryProducts).forEach(inventory::verifyStockAvailabilityFor);
    }

    /*
     * SKU 별로 배송 수량을 합산한다.
     */
    private Map<Sku, Long> mergedDeliveryQuantities(List<DeliveryProduct> deliveryProducts) {
        Map<Sku, Long> mergedDeliveryQuantities = new HashMap<>();
        for(DeliveryProduct deliveryProduct : deliveryProducts) {
            for(DeliveryProductItem deliveryProductItem : deliveryProduct.getProductItems()) {
                Sku sku = mapSkuFor(deliveryProductItem);
                mergedDeliveryQuantities.compute(sku, (skuId, quantity) -> {
                    if (Objects.nonNull(quantity)) {
                        return quantity + deliveryProduct.getQuantity();
                    }
                    return deliveryProduct.getQuantity();
                });
            }
        }
        return mergedDeliveryQuantities;
    }

    private Sku mapSkuFor(DeliveryProductItem item) {
        return inventory.getSku(item.getProductItemSkuId());
    }

    @Override
    public void dispatching(Delivery delivery) {
        // TODO 물류센터에 배달 요청한다.
    }

}
