package info.nemoworks.manteau.meta;

import lombok.Data;

import java.util.List;

@Data
public class MClass {
    private String name;
    private List<MReference> references;
    private List<MAttribute> attributes;
}
