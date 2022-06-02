package info.nemoworks.grass;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.neo4j.graphql.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class GSDLMeta {


    public static void main(String[] args) throws IOException, OptimizedQueryException {
        URL url = Resources.getResource("person.graphql");
        String sdl = Resources.toString(url, Charsets.UTF_8);

        GraphQLSchema schema = SchemaBuilder.buildSchema(sdl);
//        QueryContext ctx = new QueryContext();
        String query = """
                mutation {addPersonPets (  name: "abc", pets : "cat" ) {name}}
                 """;
        List<Cypher> c = new Translator(schema).translate(query);

        System.out.println(schema.getMutationType().getName());

    }


}
