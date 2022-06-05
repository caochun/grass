package info.nemoworks.manteau.meta;

import java.net.URL;
import java.util.List;

public interface MModel {

    public void loadModel(URL url) throws IllegalModelException;

    public List<MClass> getClasses();

}
