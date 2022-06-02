package info.nemoworks.grass;

import graphql.schema.GraphQLSchema;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

public class GSDLMeta {

    private static String sdl = """
            type Query {
                bookById(id: ID): Book
            }

            type Book {
                id: ID
                name: String
                pageCount: Int
                author: Author
            }

            type Author {
                id: ID
                firstName: String
                lastName: String
            }
            """;

    public static void main(String[] args){
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        System.out.println(typeRegistry);
    }




}
