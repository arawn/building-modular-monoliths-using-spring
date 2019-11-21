package monoliths.shipments;

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
@ComponentScan(basePackageClasses = ShipmentModuleConfiguration.class)
@EnableConfigurationProperties
public class ShipmentModuleConfiguration implements ApplicationRunner {

    @Bean
    @ConfigurationProperties("monoliths.shipments.info")
    public ModuleInfoProperties shipmentModuleInfoProperties() {
        return new ModuleInfoProperties();
    }

    @Bean
    public PublishedComponentRegisteringPostProcessor shipmentPublishedComponentRegisteringPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        return new PublishedComponentRegisteringPostProcessor(beanFactory);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Started {}", shipmentModuleInfoProperties());
    }

}
