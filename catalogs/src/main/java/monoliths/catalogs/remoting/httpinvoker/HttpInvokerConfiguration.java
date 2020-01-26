package monoliths.catalogs.remoting.httpinvoker;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import monoliths.catalogs.domain.usecase.Catalogs;
import monoliths.catalogs.domain.usecase.Inventory;

@Configuration
@ConditionalOnProperty(name = "monoliths.catalogs.remoting.httpinvoker.enabled", havingValue = "true", matchIfMissing = false)
public class HttpInvokerConfiguration {

    public static final String PATH_CATALOGS = "/httpinvoker/Catalogs";
    public static final String PATH_INVENTORY = "/httpinvoker/Inventory";

    @Bean(PATH_CATALOGS)
    HttpInvokerServiceExporter catalogsHttpInvokerServiceExporter(Catalogs catalogs) {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setServiceInterface(Catalogs.class);
        exporter.setService(catalogs);

        return exporter;
    }

    @Bean(PATH_INVENTORY)
    HttpInvokerServiceExporter inventoryHttpInvokerServiceExporter(Inventory inventory) {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setServiceInterface(Inventory.class);
        exporter.setService(inventory);

        return exporter;
    }
    
}