package info.nemoworks.manteau;

import com.google.common.io.Resources;
import info.nemoworks.manteau.domain.IllegalModelException;
import info.nemoworks.manteau.domain.emf.EcoreModel;

import java.net.URL;

public class Main {
    public static void main(String[] args) throws IllegalModelException {
        URL r = Resources.getResource("bowling.ecore");
        EcoreModel model = new EcoreModel();
        model.loadModel(r);
    }
}
