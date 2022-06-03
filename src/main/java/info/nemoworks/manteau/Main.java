package info.nemoworks.manteau;

import com.google.common.io.Resources;
import info.nemoworks.manteau.domain.IllegalModelException;
import info.nemoworks.manteau.domain.emf.EcoreModel;
import org.eclipse.emf.ecore.EClass;

import java.net.URL;

public class Main {
    public static void main(String[] args) throws IllegalModelException {
        URL r = Resources.getResource("bowling.ecore");
        EcoreModel model = new EcoreModel();
        model.loadModel(r);
        System.out.print(((EClass)(model.getEPackage().getEClassifiers().get(0))).getEAllAttributes().get(0).getEAttributeType().getInstanceClassName());
    }
}
