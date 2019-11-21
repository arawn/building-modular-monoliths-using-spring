package monoliths.orders.domain.entity;

import monoliths.commons.model.OrderSheet;

import java.util.List;

public interface OrderProductMapper {

    List<OrderProduct> mapFrom(List<OrderSheet.OrderSheetItem> orderSheetItems);

}
