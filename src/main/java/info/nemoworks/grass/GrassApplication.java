package info.nemoworks.grass;

import info.nemoworks.grass.meta.GClass;
import info.nemoworks.grass.storage.arcadedb.ArcadeRepository;
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

        arcadeRepository.saveClass(GClass.builder().name("abc1").build());
        arcadeRepository.getClass("abc1");
    }
}
