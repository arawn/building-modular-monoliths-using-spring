package monoliths.commons.model;

import lombok.Builder;
import lombok.Value;

@Value
public class DeliveryLocation {

    private String postCode;
    private String baseAddress;
    private String detailAddress;

    private boolean roadNameAddressType;

    @Builder
    public DeliveryLocation(String postCode, String baseAddress, String detailAddress, boolean roadNameAddressType) {
        this.postCode = postCode;
        this.baseAddress = baseAddress;
        this.detailAddress = detailAddress;
        this.roadNameAddressType = roadNameAddressType;
    }

}