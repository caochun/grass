package info.nemoworks.manteau.core;

public interface ModelStorage {

    public boolean save(GObject data);

    public GObject get(String className, String id);

    public boolean addRelation(GReference gReference);
}
