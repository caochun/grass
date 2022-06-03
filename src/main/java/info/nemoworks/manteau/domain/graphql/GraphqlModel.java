package info.nemoworks.manteau.domain.graphql;

import info.nemoworks.manteau.domain.IllegalModelException;
import info.nemoworks.manteau.domain.MClass;
import info.nemoworks.manteau.domain.MModel;

import java.net.URL;
import java.util.List;

public class GraphqlModel implements MModel {

    @Override
    public void loadModel(URL url) throws IllegalModelException {

    }

    @Override
    public List<MClass> getClasses() {
        return null;
    }
}
