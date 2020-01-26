package monoliths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

import monoliths.catalogs.EnableCatalogModule;

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableCatalogModule
public class CatalogsServiceApplication {

    public static final String PROPS_CONFIG_NAME = "spring.config.name: application, catalogs";

    public static void main(String[] args) {
        SpringApplication  application = new SpringApplicationBuilder()
                .properties(PROPS_CONFIG_NAME)
                .sources(CatalogsServiceApplication.class)
                .build();

        application.run(args);
    }

}