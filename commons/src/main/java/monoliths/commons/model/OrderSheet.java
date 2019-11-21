package monoliths.commons.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Data
@Setter(AccessLevel.PRIVATE)
public class OrderSheet {

    private UUID customerId;
    private List<OrderSheetItem> items;

    private Sender sender;
    private Receiver receiver;
    private DeliveryMethod deliveryMethod;
    private DeliveryLocation deliveryLocation;
    private String deliveryNote;

    private OrderSheet() { }

    @Builder
    public OrderSheet(UUID customerId, List<OrderSheetItem> items, DeliveryMethod deliveryMethod, DeliveryLocation deliveryLocation, String deliveryNote) {
        this.customerId = customerId;
        this.items = items;
        this.deliveryMethod = deliveryMethod;
        this.deliveryLocation = deliveryLocation;
        this.deliveryNote = deliveryNote;
    }

    @Data
    @Setter(AccessLevel.PRIVATE)
    public static class OrderSheetItem {

        private UUID productId;
        private int quantity;

        private OrderSheetItem() { }

        @Builder
        public OrderSheetItem(UUID productId, int quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }

    }

}
