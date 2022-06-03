package info.nemoworks.manteau.domain.emf;

import com.google.common.io.Resources;
import info.nemoworks.manteau.domain.IllegalModelException;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(@RunWith(AnnotationProcessorTestRunner.class).class)
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
