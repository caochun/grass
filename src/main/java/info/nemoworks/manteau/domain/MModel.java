package info.nemoworks.manteau.domain;

import java.net.URL;
import java.util.List;

public interface MModel {

    public void loadModel(URL url) throws IllegalModelException;

    public List<MClass> getClasses();

}
