package info.nemoworks.manteau.domain;

import lombok.Data;

import java.util.List;

@Data
public class MClass {
    private String name;
    private List<MReference> references;
    private List<MAttribute> attributes;
}
