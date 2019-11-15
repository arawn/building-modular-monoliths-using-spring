package monoliths.shipments.domain.entity;

import lombok.Getter;

@Getter
public enum DeliveryStatus {

    READY_FOR_DISPATCH("ready_for_dispatch", "발송대기"),
    DISPATCHED("dispatched", "발송됨"),
    COMPLETED("completed", "배달됨"),
    CANCELED("canceled", "배달취소됨");

    private final String code;
    private final String text;

    DeliveryStatus(String code, String text) {
        this.code = code;
        this.text = text;
    }

}
