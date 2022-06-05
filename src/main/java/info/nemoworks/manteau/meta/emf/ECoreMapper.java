package info.nemoworks.manteau.meta.emf;

import info.nemoworks.manteau.meta.MAttribute;
import info.nemoworks.manteau.meta.MClass;
import info.nemoworks.manteau.meta.MReference;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EReference;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ECoreMapper {
    ECoreMapper INSTANCE = Mappers.getMapper(ECoreMapper.class);

    @Mappings({
            @Mapping(source = "EReferences", target = "references"),
            @Mapping(source = "EAttributes", target = "attributes")
    })
    public MClass toMclass(EClass eClass);

    @Mapping(source = "EAttributeType", target = "type", qualifiedByName = "typeToString")
    public MAttribute toMAttribute(EAttribute eAttribute);

    @Named(value = "typeToString")
    public static String typeToString(@NotNull EDataType eType) {
        System.out.println("mapping" + eType.getInstanceTypeName());
        return eType.getInstanceTypeName();
    }

    @Mappings({
            @Mapping(source = "EReferenceType.name", target = "referee"),
            @Mapping(source = "name", target = "refName"),
            @Mapping(source = "upperBound", target = "many", qualifiedByName = "upperBoundToIsMany")
    })
    public MReference toMReference(EReference eReference);

    @Named("upperBoundToIsMany")
    public static boolean isMany(int upperBound) {
        if ((upperBound == -1) || (upperBound > 1)) {
            return true;
        } else {
            return false;
        }
    }
}
