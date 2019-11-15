package monoliths.shipments.domain.entity;

import monoliths.commons.model.OrderSheet;

import java.util.List;

public interface DeliveryProductMapper {

    List<DeliveryProduct> mapFrom(List<OrderSheet.OrderSheetItem> orderSheetItems);

}
