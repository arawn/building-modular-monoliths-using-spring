package monoliths.shipments.domain.entity;

import lombok.*;
import monoliths.commons.model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
public class Delivery {

    private UUID id;
    private UUID orderId;
    private Sender sender;
    private Receiver receiver;
    private DeliveryMethod method;
    private DeliveryLocation location;
    private List<DeliveryProduct> products = new ArrayList<>();
    private BigDecimal fee;
    private String note;
    private DeliveryStatus status;
    private LocalDateTime lastModified;

    private Delivery() { }

    @Builder
    private Delivery(UUID orderId, Sender sender, Receiver receiver, DeliveryMethod method, DeliveryLocation location, List<DeliveryProduct> products, BigDecimal fee, String note) {
        setId(UUID.randomUUID());
        setOrderId(Objects.requireNonNull(orderId));
        setSender(sender);
        setReceiver(receiver);
        setMethod(method);
        setLocation(location);
        setProducts(products);
        setFee(fee);
        setNote(note);

        changeDeliveryStatus(DeliveryStatus.READY_FOR_DISPATCH);
    }

    public Delivery dispatched(DistributionCenter distributionCenter) {
        distributionCenter.dispatching(this);
        return changeDeliveryStatus(DeliveryStatus.DISPATCHED);
    }

    public Delivery delivered() {
        return changeDeliveryStatus(DeliveryStatus.COMPLETED);
    }

    private Delivery changeDeliveryStatus(DeliveryStatus deliveryStatus) {
        setStatus(deliveryStatus);
        setLastModified(LocalDateTime.now());

        return this;
    }

    public static Delivery prepare(UUID orderId, OrderSheet orderSheet, DeliveryProductMapper productMapper, DeliveryFeeCalculator feeCalculator, DeliveryValidator deliveryValidator) {
        Delivery delivery = Delivery.builder()
                                    .orderId(orderId)
                                    .sender(orderSheet.getSender())
                                    .receiver(orderSheet.getReceiver())
                                    .method(orderSheet.getDeliveryMethod())
                                    .location(orderSheet.getDeliveryLocation())
                                    .products(productMapper.mapFrom(orderSheet.getItems()))
                                    .fee(feeCalculator.calculateDeliveryFee(orderSheet))
                                    .note(orderSheet.getDeliveryNote())
                                    .build();

        return deliveryValidator.validate(delivery);
    }

}
