package info.nemoworks.manteau.meta.graphql;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.language.ObjectTypeDefinition;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import info.nemoworks.manteau.meta.IllegalModelException;
import info.nemoworks.manteau.meta.MClass;
import info.nemoworks.manteau.meta.MModel;

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
