package monoliths.catalogs;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import monoliths.catalogs.domain.usecase.Catalogs;
import monoliths.catalogs.domain.usecase.Inventory;
import monoliths.catalogs.remoting.httpinvoker.HttpInvokerConfiguration;
import monoliths.context.beans.Published;
import monoliths.context.beans.PublishedComponentRegisteringPostProcessor;

@Configuration
public class CatalogContextConfiguration {

    @Bean
    PublishedComponentRegisteringPostProcessor catalogPublishedComponentRegisteringPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        return new PublishedComponentRegisteringPostProcessor(beanFactory);
    }

    @Bean
    @Published
    public HttpInvokerProxyFactoryBean catalogs() {
        HttpInvokerProxyFactoryBean factoryBean = new HttpInvokerProxyFactoryBean();
        factoryBean.setServiceUrl("http://127.0.0.1:8090" + HttpInvokerConfiguration.PATH_CATALOGS);
        factoryBean.setServiceInterface(Catalogs.class);

        return factoryBean;
    }

    @Bean
    @Published
    public HttpInvokerProxyFactoryBean inventory() {
        HttpInvokerProxyFactoryBean factoryBean = new HttpInvokerProxyFactoryBean();
        factoryBean.setServiceUrl("http://127.0.0.1:8090" + HttpInvokerConfiguration.PATH_INVENTORY);
        factoryBean.setServiceInterface(Inventory.class);

        return factoryBean;
    }

}
