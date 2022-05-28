package info.nemoworks.grass;

import com.arcadedb.database.Database;
import com.arcadedb.database.DatabaseFactory;
import info.nemoworks.grass.core.GMetaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {


    public static final String DB = "./grassdb/";

    @Bean
    public Database database() {
        DatabaseFactory databaseFactory = new DatabaseFactory(DB);
        if (databaseFactory.exists()) {
            return databaseFactory.open();
        } else {
            return databaseFactory.create();
        }
    }

    @Bean
    public GMetaModel model(@Autowired String modelUri, @Autowired String modelNs ) throws Exception {

        return new GMetaModel(modelUri,modelNs);

    }

    @Bean
    public String modelUri(){
        return "src/main/resources/bowling.ecore";
    }

    @Bean
    public String modelNs(){
        return "http://org/eclipse/example/bowling";
    }
}
