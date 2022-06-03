package info.nemoworks.manteau.domain.emf;

import info.nemoworks.manteau.domain.MAttribute;
import info.nemoworks.manteau.domain.MReference;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EReferenceMapper {

    EReferenceMapper INSTANCE = Mappers.getMapper(EReferenceMapper.class);

    @Mappings({
            @Mapping(source = "EReferenceType", target = "referee")
    })
    public MReference toMReference(EReference eReference);
}
