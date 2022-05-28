package info.nemoworks.grass.core;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GObject extends HashMap<String, Object> {

    private String className;

    public GObject(String className) {
        super();
        this.className = className;
        this.put("_id", UUID.randomUUID().toString());
    }

    public String getClassName() {
        return this.className;
    }

    public String getId() {
        return (String) this.get("_id");
    }

    public static GObject fromMap(String className, Map<String, Object> attributes){
        GObject gObject = new GObject(className);
        gObject.putAll(attributes);
        return gObject;
    }
}
