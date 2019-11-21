package monoliths.commons.model;

import lombok.Builder;
import lombok.Value;

@Value
public class Sender {

    private String name;
    private String telephone;
    private String secondTelephone;

    @Builder
    public Sender(String name, String telephone, String secondTelephone) {
        this.name = name;
        this.telephone = telephone;
        this.secondTelephone = secondTelephone;
    }

}