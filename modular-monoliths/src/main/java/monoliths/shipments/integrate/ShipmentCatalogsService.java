package monoliths.shipments.integrate;

import lombok.AllArgsConstructor;
import monoliths.catalogs.domain.entity.Product;
import monoliths.catalogs.domain.usecase.Catalogs;
import monoliths.commons.model.OrderSheet;
import monoliths.shipments.domain.entity.DeliveryProduct;
import monoliths.shipments.domain.entity.DeliveryProductItem;
import monoliths.shipments.domain.entity.DeliveryProductMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ShipmentCatalogsService implements DeliveryProductMapper {

    private Catalogs catalogs;

    @Override
    public List<DeliveryProduct> mapFrom(List<OrderSheet.OrderSheetItem> orderSheetItems) {
        return orderSheetItems.stream().map(orderSheetItem -> {
            Product product = mapProductFor(orderSheetItem);
            List<DeliveryProductItem> productItems = product.getItems().stream().map(productItem ->
                    DeliveryProductItem.builder()
                                       .productItemId(productItem.getId())
                                       .productItemSkuId(productItem.getSku().getId())
                                       .build()).collect(Collectors.toList());

            return DeliveryProduct.builder()
                                  .productId(product.getId())
                                  .productItems(productItems)
                                  .quantity(orderSheetItem.getQuantity())
                                  .build();
        }).collect(Collectors.toList());
    }

    private Product mapProductFor(OrderSheet.OrderSheetItem item) {
        return catalogs.getProduct(item.getProductId());
    }

}
