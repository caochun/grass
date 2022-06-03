package info.nemoworks.manteau;

import com.google.common.io.Resources;
import info.nemoworks.manteau.domain.IllegalModelException;
import info.nemoworks.manteau.domain.emf.EcoreModel;
import info.nemoworks.manteau.domain.graphql.GraphqlModel;
import org.eclipse.emf.ecore.EClass;

import java.net.URL;

public class Main {
    public static void main(String[] args) throws IllegalModelException {
        URL r = Resources.getResource("bowling.ecore");
        EcoreModel model = new EcoreModel();
        model.loadModel(r);

        URL s = Resources.getResource("person.graphql");
        GraphqlModel graphqlModel = new GraphqlModel();
        graphqlModel.loadModel(s);
        graphqlModel.getClasses();

    }
}
