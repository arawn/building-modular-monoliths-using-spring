package monoliths.orders;

import monoliths.commons.model.DeliveryLocation;
import monoliths.commons.model.DeliveryMethod;
import monoliths.commons.model.OrderSheet;

import java.util.Arrays;
import java.util.UUID;

public class Fixtures {

    public static OrderSheet.OrderSheetBuilder builderOrderSheet() {
        return OrderSheet.builder()
                         .customerId(UUID.randomUUID())
                         .items(Arrays.asList(builderOrderSheetItem().build(), builderOrderSheetItem().quantity(2).build()))
                         .deliveryMethod(DeliveryMethod.INSTANTLY)
                         .deliveryLocation(builderDeliveryLocation().build())
                         .deliveryNote("부재시 현관문 앞에 놔주세요.");
    }

    public static OrderSheet.OrderSheetItem.OrderSheetItemBuilder builderOrderSheetItem() {
        return OrderSheet.OrderSheetItem.builder()
                         .productId(UUID.randomUUID())
                         .quantity(1);
    }

    public static DeliveryLocation.DeliveryLocationBuilder builderDeliveryLocation() {
        return DeliveryLocation.builder()
                               .postCode("63364")
                               .baseAddress("제주 제주시 구좌읍 종달논길 32")
                               .detailAddress("종달어촌계")
                               .roadNameAddressType(true);
    }

}
