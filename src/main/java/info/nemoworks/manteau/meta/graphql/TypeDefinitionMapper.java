package info.nemoworks.manteau.meta.graphql;

import graphql.language.ObjectTypeDefinition;
import info.nemoworks.manteau.meta.MClass;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TypeDefinitionMapper {

    public static TypeDefinitionMapper INSTANCE = Mappers.getMapper(TypeDefinitionMapper.class);

    @Mappings({
//            @Mapping(source = "fieldDefinitions", target = "attributes")
    })
    public MClass toMclass(ObjectTypeDefinition typeDefinition);

//    @Mappings({
//            @Mapping(source = "type", target = "attributes")
//    })
//    public MAttribute toMattribute(FieldDefinition fieldDefinition);
}
