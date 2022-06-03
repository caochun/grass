package info.nemoworks.manteau.domain;

import lombok.Data;

@Data
public class MReference {
    private String refName;
    private DIRECTION direction;

    private MClass referee;

    public static enum DIRECTION {
        IN, OUT
    }
}
