package info.nemoworks.manteau.domain.emf;

import info.nemoworks.manteau.domain.MAttribute;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EAttributeMapper {

    EAttributeMapper INSTANCE = Mappers.getMapper(EAttributeMapper.class);

    @Mapping(source = "eAttribute.EAttributeType", target = "type", qualifiedByName = "typeToString")
    public MAttribute toMattribute(EAttribute eAttribute);

    @Named("typeToString")
    public static String map(@NotNull EDataType eDataType) {
        System.out.println("mapping" + eDataType.toString());
        return eDataType.getName();

    }
}
