package info.nemoworks.manteau.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emfcloud.jackson.databind.EMFContext;
import org.eclipse.emfcloud.jackson.module.EMFModule;
import org.eclipse.emfcloud.jackson.resource.JsonResourceFactory;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class GMeta {

    private EPackage ePackage;
    private Resource resource;

    private ObjectMapper mapper;

    private ObjectMapper getMapper() {
        if (mapper == null)
            mapper = new ObjectMapper();
        return mapper;
    }

    public GMeta(String modelUri, String modelNs) throws Exception {

        ResourceSet resourceSet = new ResourceSetImpl();

        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());

        EcorePackage.eINSTANCE.eClass();

        // Get the URI of the model file.
        URI fileURI = URI.createFileURI(modelUri);

        // Demand load the resource for this file.
        Resource resource = resourceSet.getResource(fileURI, true);

        if (resource == null) {
            throw new Exception("Resource creation fails");
        }

        this.resource = resource;

        this.ePackage = (EPackage) resource.getContents().get(0);


        if (this.ePackage == null) {
            throw new Exception("Model initialization fails.");
        }
        resourceSet.getPackageRegistry().put(modelNs, ePackage);

    }

    public EClass getEClass(String name) {
        EClassifier eClassifier = this.ePackage.getEClassifier(name);
        if (eClassifier instanceof EClass) return (EClass) eClassifier;
        return null;
    }

    public List<EClass> getEClasses() {
        EList<EClassifier> eClassifiers = this.ePackage.getEClassifiers();
        return eClassifiers.stream().filter(c -> c instanceof EClass).map(c -> (EClass) c).collect(Collectors.toList());
    }

    public List<String> getClassNames() {
        return this.getEClasses().stream().map(c -> c.getName()).collect(Collectors.toList());
    }

    public List<EReference> getReferences(EClass eClass) {
        return eClass.getEAllReferences();
    }

    public List<String> getReferenceNames(String className) {
        return getReferences(this.getEClass(className)).stream().map(r -> r.getName()).collect(Collectors.toList());
    }

//    public List<EReference> getAllReferences() {
//        return this.getClasses().stream().flatMap(eClass -> Stream.of(eClass.getEAllReferences())).;
//    }

    public String toJsonString() throws JsonProcessingException {
        EMFModule module = new EMFModule();
        getMapper().registerModule(module);
        JsonResourceFactory factory = new JsonResourceFactory(getMapper());

        return getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(resource);

    }

    public JsonNode toJson() throws JsonProcessingException {
        EMFModule module = new EMFModule();
        getMapper().registerModule(module);
        JsonResourceFactory factory = new JsonResourceFactory(getMapper());

        return getMapper().valueToTree(resource);

    }


    public Object toObject(String jsonStr) throws IOException {
        JsonNode actualObj = getMapper().readTree(jsonStr);

        Object obj = mapper.reader()
                .withAttribute(EMFContext.Attributes.RESOURCE, resource)
                .forType(Object.class)
                .readValue(actualObj);
        return obj;
    }
}
