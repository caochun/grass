package info.nemoworks.grass;

import com.arcadedb.database.Database;
import com.arcadedb.database.DatabaseFactory;
import info.nemoworks.grass.core.GMeta;
import info.nemoworks.grass.core.GModelFactory;
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
    public GMeta model() throws Exception {
        return new GMeta("src/main/resources/bowling.ecore", "http://org/eclipse/example/bowling");
    }

    @Bean
    public GModelFactory modelFactory(@Autowired GMeta gMeta) {
        return GModelFactory.getInstance(gMeta);
    }
}
