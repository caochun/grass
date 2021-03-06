package info.nemoworks.manteau.meta.emf;

import com.google.common.io.Resources;
import info.nemoworks.manteau.meta.IllegalModelException;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EcoreModelTests {
    @Test
    public void testLoadEcoreModel() throws IllegalModelException {

        URL r =  Resources.getResource("bowling.ecore");

        assertNotNull(r);

        EcoreModel model = new EcoreModel();
        model.loadModel(r);

        assertNotNull(model.getClasses());

    }
}
