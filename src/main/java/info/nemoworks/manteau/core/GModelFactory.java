package info.nemoworks.manteau.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import info.nemoworks.manteau.core.exception.InvalidReferenceException;
import info.nemoworks.manteau.core.exception.NoSuchClassException;
import org.eclipse.emf.ecore.EReference;

import java.util.Iterator;
import java.util.List;

public class GModelFactory {

    private static GModelFactory instance;

    public static GModelFactory getInstance(GMeta gMeta) {
        if (instance == null) {
            instance = new GModelFactory(gMeta);
        }
        return instance;
    }

    private GMeta gMeta;

    private GModelFactory(GMeta gMeta) {
        this.gMeta = gMeta;
    }

    public GObject createObject(String className) throws NoSuchClassException {

        if (this.gMeta.getEClass(className) == null) {
            throw new NoSuchClassException();
        }
        return new GObject(className);
    }

    public GReference createRef(String refName, GObject from, GObject to) throws InvalidReferenceException {

        List<EReference> referenceList = gMeta.getEClass(from.getClassName()).getEAllReferences();

        if (referenceList.stream().filter(r -> r.getName().equals(refName)).count() == 0)
            throw new InvalidReferenceException();

        if (referenceList.stream().filter(r -> r.getEReferenceType().getName().equals(to.getClassName())).count() == 0)
            throw new InvalidReferenceException();

        return new GReference(refName, from, to);

    }

    public JsonNode getClassSchema(String className) throws JsonProcessingException {
        Iterator<JsonNode> i  = this.gMeta.toJson().get("eClassifiers").iterator();
        while (i.hasNext()){
            JsonNode node = i.next();
            if (node.get("name").asText().equals(className))
                return node;
        }
        return null;
    }
}
