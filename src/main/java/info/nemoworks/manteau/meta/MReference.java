package info.nemoworks.manteau.meta;

import lombok.Data;

@Data
public class MReference {
    private String refName;
    private DIRECTION direction = DIRECTION.OUT;

    private boolean many;

    private String referee;

    public static enum DIRECTION {
        IN, OUT
    }
}
