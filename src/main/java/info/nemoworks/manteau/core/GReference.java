package info.nemoworks.manteau.core;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GReference {
    private String refName;

    private GObject from;
    private GObject to;

}
