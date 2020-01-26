package monoliths.orders.integrate;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import monoliths.commons.model.OrderSheet;
import monoliths.orders.domain.entity.OrderProduct;
import monoliths.orders.domain.entity.OrderProductItem;
import monoliths.orders.domain.entity.OrderProductMapper;
import monoliths.orders.integrate.catalogs.Product;
import monoliths.orders.integrate.catalogs.ProductRepository;

@AllArgsConstructor
public class RemoteOrderCatalogsService implements OrderProductMapper {

    private ProductRepository productRepository;

    @Override
    public List<OrderProduct> mapFrom(List<OrderSheet.OrderSheetItem> orderSheetItems) {
        return orderSheetItems.stream().map(orderSheetItem -> {
            Product product = getProductFor(orderSheetItem);
            List<OrderProductItem> productItems = product.getItems().stream().map(productItem ->
                    OrderProductItem.builder()
                                    .productItemId(productItem.getId())
                                    .productItemName(productItem.getName())
                                    .productItemPrice(productItem.getPrice())
                                    .productItemSkuId(productItem.getSkuId())
                                    .build()).collect(Collectors.toList());

            return OrderProduct.builder()
                               .productId(product.getId())
                               .productName(product.getName())
                               .productItems(productItems)
                               .price(product.getPrice())
                               .quantity(orderSheetItem.getQuantity())
                               .build();
        }).collect(Collectors.toList());
    }

    private Product getProductFor(OrderSheet.OrderSheetItem item) {
        return productRepository.getProduct(item.getProductId());
    }

}