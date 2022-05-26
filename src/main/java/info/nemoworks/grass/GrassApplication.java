package info.nemoworks.grass;

import info.nemoworks.grass.storage.arcadedb.ArcadeRepository;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrassApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(GrassApplication.class, args);
    }

    @Autowired
    public ArcadeRepository arcadeRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
//        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new XMIResourceFactoryImpl());
        EcorePackage ecorePackage = EcorePackage.eINSTANCE;

        URI uri = URI.createFileURI("src/main/resources/bowling.ecore");

        Resource myMetaModel = resourceSet.getResource(uri, true);
//        EPackage univEPackage = (EPackage) myMetaModel.getContents().get(0);
        System.out.println(myMetaModel);

    }
}
