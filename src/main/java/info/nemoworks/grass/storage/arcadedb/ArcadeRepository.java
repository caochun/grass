package info.nemoworks.grass.storage.arcadedb;

import com.arcadedb.database.Database;
import com.arcadedb.schema.VertexType;
import info.nemoworks.grass.meta.GClass;
import info.nemoworks.grass.storage.GrassRepository;
import info.nemoworks.grass.storage.exception.ClassAlreadyExistsException;
import info.nemoworks.grass.storage.exception.NoSuchClassException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ArcadeRepository implements GrassRepository {

    private Logger logger = Logger.getLogger(ArcadeRepository.class.getName());

    private Database db;

    @Autowired
    public void setDb(Database db) {
        this.db = db;
    }

    @Override
    public boolean saveClass(GClass gClass) throws ClassAlreadyExistsException {

        if (db.getSchema().existsType(gClass.getName()))
            throw new ClassAlreadyExistsException();

        try {
            db.begin();
            VertexType vertexType = db.getSchema().createVertexType(gClass.getName());
            if (!vertexType.existsProperty("class"))
                vertexType.createProperty("class", gClass.getClass());
            db.commit();

            return true;
        } catch (Exception e) {
            db.rollback();
        }

        return false;
    }

    @Override
    public GClass getClass(String name) throws NoSuchClassException {

        if (!db.getSchema().existsType(name))
            throw new NoSuchClassException();

        VertexType vertexType = db.getSchema().getOrCreateVertexType(name);
        return GClass.builder().build();

    }
}
