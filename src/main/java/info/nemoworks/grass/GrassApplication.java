package info.nemoworks.grass;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.impl.EAttributeImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xml.type.SimpleAnyType;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emfcloud.jackson.databind.EMFContext;
import org.eclipse.emfcloud.jackson.module.EMFModule;
import org.eclipse.emfcloud.jackson.resource.JsonResourceFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class GrassApplication {

    public static void main(String[] args) throws IOException {

        ResourceSet resourceSet = new ResourceSetImpl();
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());

        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());

        EcorePackage.eINSTANCE.eClass();

        // Get the URI of the model file.
        URI fileURI = URI.createFileURI("/Users/chun/Downloads/bowling.ecore");

        // Demand load the resource for this file.
        Resource resource = resourceSet.getResource(fileURI, true);


        EPackage bowlingEPackage = (EPackage) resource.getContents().get(0);


        EClass playerEClass = (EClass) bowlingEPackage.getEClassifier("Player");

        resourceSet.getPackageRegistry().put("http://org/eclipse/example/bowling", bowlingEPackage);

        EFactory bowlingInstance = bowlingEPackage.getEFactoryInstance();

        EObject playerObject = bowlingInstance.create(playerEClass);

        EList<EStructuralFeature> eStructuralFeatures =  ((EClass) bowlingEPackage.getEClassifier("Player")).getEStructuralFeatures();

        for(EStructuralFeature eStructuralFeature: eStructuralFeatures){
            if (eStructuralFeature instanceof EAttribute){
                if  (((EAttribute)eStructuralFeature).getEAttributeType().getInstanceClassName().equals("java.lang.String")){
//                    SimpleAnyType wrapper = XMLTypeFactory.eINSTANCE.createSimpleAnyType();
//                    wrapper.setInstanceType(((EAttribute)eStructuralFeature).getEAttributeType());
//                    wrapper.setValue("ABC");
                    playerObject.eSet(eStructuralFeature, "ABC");
                }


            }
        }


        ObjectMapper mapper = new ObjectMapper();
        EMFModule module = new EMFModule();
        mapper.registerModule(module);
        JsonResourceFactory factory = new JsonResourceFactory(mapper);

        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resource);

        String objectString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(playerObject);

        System.out.println(jsonString);

        System.out.println(objectString);

        String str = "{\"eClass\" : \"file:/Users/chun/Downloads/bowling.ecore#//Player\",\"name\" : \"ABC\", \"height\" : 5.0 }";

        JsonNode actualObj = mapper.readTree(str);

        Object user = mapper.reader()
                .withAttribute(EMFContext.Attributes.RESOURCE, resource)
                .forType(Object.class)
                .readValue(actualObj);

        System.out.println(user);

    }


}
