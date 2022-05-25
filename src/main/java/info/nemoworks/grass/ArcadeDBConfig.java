package info.nemoworks.grass;

import com.arcadedb.database.Database;
import com.arcadedb.database.DatabaseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArcadeDBConfig {

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
}
