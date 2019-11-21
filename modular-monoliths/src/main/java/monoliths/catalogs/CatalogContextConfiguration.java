package monoliths.catalogs;

import lombok.extern.slf4j.Slf4j;
import monoliths.context.beans.PublishedComponentRegisteringPostProcessor;
import monoliths.module.info.ModuleInfoProperties;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ComponentScan
@EnableConfigurationProperties
public class CatalogContextConfiguration implements ApplicationRunner {

    @Bean
    @ConfigurationProperties("monoliths.catalogs.info")
    public ModuleInfoProperties catalogModuleInfoProperties() {
        return new ModuleInfoProperties();
    }

    @Bean
    public PublishedComponentRegisteringPostProcessor catalogPublishedComponentRegisteringPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        return new PublishedComponentRegisteringPostProcessor(beanFactory);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Started {}", catalogModuleInfoProperties());
    }

}
