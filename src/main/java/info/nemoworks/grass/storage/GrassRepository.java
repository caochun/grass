package info.nemoworks.grass.storage;

import info.nemoworks.grass.meta.GClass;
import info.nemoworks.grass.storage.exception.ClassAlreadyExistsException;
import info.nemoworks.grass.storage.exception.NoSuchClassException;

public interface GrassRepository {

    public boolean saveClass(GClass gClass) throws ClassAlreadyExistsException;

    public GClass getClass(String name) throws NoSuchClassException;

}
