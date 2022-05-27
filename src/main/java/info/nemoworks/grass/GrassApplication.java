package info.nemoworks.grass;

import info.nemoworks.grass.core.GModel;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrassApplication {

    public static void main(String[] args) throws Exception {

        GModel gModel = new GModel("src/main/resources/bowling.ecore", "http://org/eclipse/example/bowling");

        System.out.println(gModel.getClassNames());

        System.out.println(gModel.getReferenceNames("Game"));


    }


}
