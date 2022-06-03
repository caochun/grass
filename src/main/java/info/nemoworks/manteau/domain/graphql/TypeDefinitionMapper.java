package info.nemoworks.manteau.domain.graphql;

import graphql.language.FieldDefinition;
import graphql.language.ObjectTypeDefinition;
import graphql.language.TypeDefinition;
import info.nemoworks.manteau.domain.MAttribute;
import info.nemoworks.manteau.domain.MClass;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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
