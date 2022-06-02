package info.nemoworks.grass;

import info.nemoworks.grass.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrassApplication implements ApplicationRunner {

    public static void main(String[] args) throws Exception {


        SpringApplication.run(GrassApplication.class, args);

    }

    public GMeta model;

    @Autowired
    public void setModel(GMeta model) {
        this.model = model;
    }

    public GModelFactory gModelFactory;

    @Autowired
    public void setgModelFactory(GModelFactory gModelFactory) {
        this.gModelFactory = gModelFactory;
    }

    public ModelStorage modelStorage;

    @Autowired
    public void setModelStorage(ModelStorage modelStorage) {
        this.modelStorage = modelStorage;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println(model.toJsonString());
        System.out.println(model.getClassNames());

        System.out.println(gModelFactory.getClassSchema("Player").toPrettyString());

        System.out.println(model.getReferenceNames("Game"));

        GObject p1 = gModelFactory.createObject("Player");
        p1.put("name", "张三");
        p1.put("age", "40");
        modelStorage.save(p1);

        GObject p2 = gModelFactory.createObject("Player");
        p2.put("name", "李四");
        p2.put("age", "38");
        modelStorage.save(p2);

        try {
            GReference reference1 = gModelFactory.createRef("Friend", p1, p2);
            modelStorage.addRelation(reference1);
        } catch (Exception e) {
            e.printStackTrace();
        }


        GObject l1 = gModelFactory.createObject("League");
        l1.put("name", "league1");

        modelStorage.save(l1);

        GReference reference1 = gModelFactory.createRef("players", l1, p1);

        GReference reference2 = gModelFactory.createRef("players", l1, p2);
        modelStorage.addRelation(reference1);

        modelStorage.addRelation(reference2);
    }
}
