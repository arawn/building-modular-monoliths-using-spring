package monoliths.commons.model;

import lombok.Getter;

@Getter
public enum DeliveryMethod {

    INSTANTLY("instantly", "바로배송"),
    COURIER_SERVICE("courier_service", "택배");

    private final String literal;
    private final String description;

    DeliveryMethod(String literal, String description) {
        this.literal = literal;
        this.description = description;
    }

}