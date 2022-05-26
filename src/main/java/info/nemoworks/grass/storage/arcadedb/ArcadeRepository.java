package info.nemoworks.grass.storage.arcadedb;

import com.arcadedb.database.Database;
import com.arcadedb.schema.VertexType;
import info.nemoworks.grass.meta.GType;
import info.nemoworks.grass.storage.GrassRepository;
import info.nemoworks.grass.storage.exception.ClassAlreadyExistsException;
import info.nemoworks.grass.storage.exception.NoSuchClassException;
import org.eclipse.emf.ecore.*;
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
    public boolean saveClass(GType gType) throws ClassAlreadyExistsException {

        if (db.getSchema().existsType(gType.getName()))
            throw new ClassAlreadyExistsException();

        try {
            db.begin();
            VertexType vertexType = db.getSchema().createVertexType(gType.getName());

            if (!vertexType.existsProperty("class"))
                vertexType.createProperty("class", gType.getClass());
            db.commit();

            return true;
        } catch (Exception e) {
            db.rollback();
        }

        return false;
    }

    @Override
    public GType getClass(String name) throws NoSuchClassException {

        EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
        ePackage.setName("library");
        ePackage.setNsPrefix("library");
        ePackage.setNsURI("http://library/1.0");
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("Book");
        EAttribute eAttr = EcoreFactory.eINSTANCE.createEAttribute();
        eAttr.setName("pages");
        eAttr.setEType(EcorePackage.Literals.EINT);
        eClass.getEStructuralFeatures().add(eAttr);
        ePackage.getEClassifiers().add(eClass);
        EReference eReference = EcoreFactory.eINSTANCE.createEReference();

        if (!db.getSchema().existsType(name))
            throw new NoSuchClassException();

        VertexType vertexType = db.getSchema().getOrCreateVertexType(name);
        return GType.builder().build();

    }
}
