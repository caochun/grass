package info.nemoworks.manteau.app;

import graphql.schema.GraphQLList;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLType;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.SessionConfig;
import org.neo4j.graphql.Cypher;
import org.neo4j.graphql.DataFetchingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Configuration of the DataFetchingInterceptor
 */
@Configuration
public class Neo4jConfiguration {

    /**
     * This interceptor is bound to all the graphql fields generated by the neo4j-graphql-library.
     * Its purpose is the execution of the cypher query and the transformation of the query result.
     */
    @Bean
    public DataFetchingInterceptor dataFetchingInterceptor(
            Driver driver,
            @Value("${database}") String database) {
        return (env, delegate) -> {
            Cypher cypher = delegate.get(env);
            return driver.session(SessionConfig.forDatabase(database)).writeTransaction(tx -> {
                Map<String, Object> boltParams = new HashMap<>(cypher.getParams());
                boltParams.replaceAll((key, value) -> toBoltValue(value));

                Result result = tx.run(cypher.getQuery(), boltParams);
                if (isListType(cypher.getType())) {
                    return result.list()
                            .stream()
                            .map(record -> record.get(cypher.getVariable()).asObject())
                            .collect(Collectors.toList());
                } else {
                    return result.list()
                            .stream()
                            .map(record -> record.get(cypher.getVariable()).asObject())
                            .collect(Collectors.toList())
                            .stream().findFirst()
                            .orElse(Collections.emptyMap());
                }
            });
        };
    }

    private Object toBoltValue(Object value) {
        if (value instanceof BigInteger) {
            return ((BigInteger) value).longValueExact();
        }
        if (value instanceof BigDecimal) {
            return ((BigDecimal) value).doubleValue();
        }
        return value;
    }

    private boolean isListType(GraphQLType type) {
        if (type instanceof GraphQLList) {
            return true;
        }
        return type instanceof GraphQLNonNull
                && this.isListType(((GraphQLNonNull) type).getWrappedType());
    }
}
