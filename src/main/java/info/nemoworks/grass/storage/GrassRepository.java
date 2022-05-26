package info.nemoworks.grass.storage;

import info.nemoworks.grass.meta.GType;
import info.nemoworks.grass.storage.exception.ClassAlreadyExistsException;
import info.nemoworks.grass.storage.exception.NoSuchClassException;

public interface GrassRepository {

    public boolean saveClass(GType gType) throws ClassAlreadyExistsException;

    public GType getClass(String name) throws NoSuchClassException;

}
