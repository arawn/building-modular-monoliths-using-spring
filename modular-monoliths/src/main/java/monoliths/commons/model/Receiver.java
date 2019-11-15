package monoliths.commons.model;

import lombok.Builder;
import lombok.Value;

@Value
public class Receiver {

    private String name;
    private String telephone;
    private String secondTelephone;

    @Builder
    public Receiver(String name, String telephone, String secondTelephone) {
        this.name = name;
        this.telephone = telephone;
        this.secondTelephone = secondTelephone;
    }

}