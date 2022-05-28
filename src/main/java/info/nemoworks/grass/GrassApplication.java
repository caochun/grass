package info.nemoworks.grass;

import info.nemoworks.grass.core.GMetaModel;
import info.nemoworks.grass.core.GObject;
import info.nemoworks.grass.core.ModelStorage;
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

    public GMetaModel model;

    @Autowired
    public void setModel(GMetaModel model) {
        this.model = model;
    }

    public ModelStorage modelStorage;

    @Autowired
    public void setModelStorage(ModelStorage modelStorage) {
        this.modelStorage = modelStorage;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println(model.getClassNames());

        System.out.println(model.getReferenceNames("Game"));

        GObject p1 = new GObject("Player");
        p1.put("name", "张三");
        p1.put("age", "40");

        GObject p2 = new GObject("Player");
        p2.put("name", "李四");
        p2.put("age", "38");

        modelStorage.save(p1);

        p1 = modelStorage.get("Player", p1.getId());

        modelStorage.save(p2);
        modelStorage.addRelation(p1, p2, "朋友");

    }
}
