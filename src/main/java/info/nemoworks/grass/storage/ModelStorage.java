package info.nemoworks.grass.storage;

import info.nemoworks.grass.core.GObject;

public interface ModelStorage {

    public boolean save(GObject data);

    public GObject get(String className, String id);

    public boolean addRelation(GObject from, GObject to, String relation);
}
