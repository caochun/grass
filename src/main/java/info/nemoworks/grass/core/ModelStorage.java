package info.nemoworks.grass.core;

import info.nemoworks.grass.core.GObject;

public interface ModelStorage {

    public boolean save(GObject data);

    public GObject get(String className, String id);

    public boolean addRelation(GReference gReference);
}
