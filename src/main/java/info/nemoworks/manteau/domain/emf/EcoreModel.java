package info.nemoworks.manteau.domain.emf;

import info.nemoworks.manteau.domain.IllegalModelException;
import info.nemoworks.manteau.domain.MClass;
import info.nemoworks.manteau.domain.MModel;
import lombok.Getter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class EcoreModel implements MModel {

    @Getter
    private EPackage ePackage;

    @Getter
    private List<MClass> mClasses;
    @Override
    public void loadModel(URL url) throws IllegalModelException {

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
        EcorePackage.eINSTANCE.eClass();

        // Get the URI of the model file.
        URI fileURI = URI.createFileURI(url.getFile());

        // Demand load the resource for this file.
        Resource eResource = resourceSet.getResource(fileURI, true);

        if (eResource == null) {
            throw new IllegalModelException("Ecore resource creation fails");
        }

        this.ePackage = (EPackage) eResource.getContents().get(0);

        if (this.ePackage == null) {
            throw new IllegalModelException("Ecore model initialization fails.");
        }
        resourceSet.getPackageRegistry().put(ePackage.getNsURI(), ePackage);

        this.mClasses = getClasses();
    }

    @Override
    public List<MClass> getClasses() {
        EClassMapper mapper = EClassMapper.INSTANCE;
        EList<EClassifier> eClassifiers = this.ePackage.getEClassifiers();
        return eClassifiers.stream().filter(c -> c instanceof EClass).map(c -> mapper.toMclass((EClass) c)).collect(Collectors.toList());
    }
}
