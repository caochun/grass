package info.nemoworks.grass.meta;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
public class GClass {

    @Getter
    private String name;

    @Getter
    private List<GAttribute> attributes;

    @Getter
    private List<GRelation> relations;
}
