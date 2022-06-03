package info.nemoworks.manteau.domain.emf;

import info.nemoworks.manteau.domain.MClass;
import org.eclipse.emf.ecore.EClass;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EClassMapper {
    EClassMapper INSTANCE = Mappers.getMapper( EClassMapper.class );

    @Mappings({
            @Mapping(source = "EReferences", target = "references"),
            @Mapping(source = "EAttributes", target = "attributes")
    })
    public MClass toMclass(EClass eClass);
}
