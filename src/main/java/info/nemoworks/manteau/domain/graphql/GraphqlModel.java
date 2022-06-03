package info.nemoworks.manteau.domain.graphql;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.language.ObjectTypeDefinition;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import info.nemoworks.manteau.domain.IllegalModelException;
import info.nemoworks.manteau.domain.MClass;
import info.nemoworks.manteau.domain.MModel;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class GraphqlModel implements MModel {

    TypeDefinitionRegistry typeDefinitionRegistry;

    @Override
    public void loadModel(URL url) throws IllegalModelException {
        String sdl;
        try {
            sdl = Resources.toString(url, Charsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalModelException(e.getMessage());
        }

        SchemaParser schemaParser = new SchemaParser();

        typeDefinitionRegistry = schemaParser.parse(sdl);


    }

    @Override
    public List<MClass> getClasses() {
        return typeDefinitionRegistry.types().values().stream().map(t -> TypeDefinitionMapper.INSTANCE.toMclass((ObjectTypeDefinition) t)).collect(Collectors.toList());
    }
}
