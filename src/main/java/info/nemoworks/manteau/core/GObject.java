package info.nemoworks.manteau.core;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GObject extends HashMap<String, Object> {

    private String className;
    private String id;

    public GObject(String className) {
        super();
        this.className = className;
        this.put("_id", UUID.randomUUID().toString());
        this.put("_class", className);
    }

    public String getClassName() {
        if (this.className == null)
            this.className = (String) (this.get("_class"));
        return this.className;
    }

    public String getId() {
        if (this.id == null)
            this.id = (String) this.get("_id");
        return this.id;
    }

    public static GObject fromMap(String className, Map<String, Object> attributes) {
        GObject gObject = new GObject(className);
        gObject.putAll(attributes);
        return gObject;
    }
}
