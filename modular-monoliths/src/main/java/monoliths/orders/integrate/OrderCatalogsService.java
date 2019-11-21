package monoliths.orders.integrate;

import lombok.AllArgsConstructor;
import monoliths.catalogs.domain.entity.Product;
import monoliths.catalogs.domain.entity.ProductRepository;
import monoliths.catalogs.domain.usecase.Catalogs;
import monoliths.commons.SystemException;
import monoliths.commons.model.OrderSheet;
import monoliths.orders.domain.entity.OrderProduct;
import monoliths.orders.domain.entity.OrderProductItem;
import monoliths.orders.domain.entity.OrderProductMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
class OrderCatalogsService implements OrderProductMapper {

    private Catalogs catalogs;

    @Override
    public List<OrderProduct> mapFrom(List<OrderSheet.OrderSheetItem> orderSheetItems) {
        return orderSheetItems.stream().map(orderSheetItem -> {
            Product product = getProductFor(orderSheetItem);
            List<OrderProductItem> productItems = product.getItems().stream().map(productItem ->
                    OrderProductItem.builder()
                                    .productItemId(productItem.getId())
                                    .productItemName(productItem.getName())
                                    .productItemPrice(productItem.getPrice())
                                    .productItemSkuId(productItem.getSku().getId())
                                    .build()).collect(Collectors.toList());

            return OrderProduct.builder()
                               .productId(product.getId())
                               .productName(product.getName())
                               .productItems(productItems)
                               .price(product.calculatePrice())
                               .quantity(orderSheetItem.getQuantity())
                               .build();
        }).collect(Collectors.toList());
    }

    private Product getProductFor(OrderSheet.OrderSheetItem item) {
        return catalogs.getProduct(item.getProductId());
    }

}
