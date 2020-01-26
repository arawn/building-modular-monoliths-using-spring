package monoliths.catalogs.remoting.httpinvoker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.web.util.UriComponentsBuilder;

import monoliths.catalogs.CatalogFixtures;
import monoliths.catalogs.CatalogModuleConfiguration;
import monoliths.catalogs.domain.entity.CategoryRepository;
import monoliths.catalogs.domain.entity.Product;
import monoliths.catalogs.domain.entity.ProductRepository;
import monoliths.catalogs.domain.entity.SkuRepository;
import monoliths.catalogs.domain.usecase.Catalogs;
import monoliths.catalogs.remoting.httpinvoker.HttpInvokerServiceTest.HttpInvokerServiceTestConfiguration;

@SpringBootTest(
    classes = HttpInvokerServiceTestConfiguration.class, 
    properties = "monoliths.catalogs.remoting.httpinvoker.enabled=true",
    webEnvironment = WebEnvironment.RANDOM_PORT
)
public class HttpInvokerServiceTest {
    
    @LocalServerPort int serverPort;
    @Autowired ProductRepository ProductRepository;

    @Test
    void catalogsHttpInvokerService() throws Exception {
        String uri = UriComponentsBuilder.fromPath(HttpInvokerConfiguration.PATH_CATALOGS)
                                         .scheme("http")
                                         .host("127.0.0.1")
                                         .port(serverPort)
                                         .build()
                                         .toUriString();

        HttpInvokerProxyFactoryBean factoryBean = new HttpInvokerProxyFactoryBean();
        factoryBean.setServiceUrl(uri);
        factoryBean.setServiceInterface(Catalogs.class);
        factoryBean.afterPropertiesSet();

        Catalogs catalogs = (Catalogs) factoryBean.getObject();
        Product product = catalogs.getProductByCode(CatalogFixtures.SIMPLE_PRODUCT_CODE);

        assertEquals(product.getCode(), CatalogFixtures.SIMPLE_PRODUCT_CODE);
    }


    @Configuration
    @EnableAutoConfiguration
    @Import(CatalogModuleConfiguration.class)
    static class HttpInvokerServiceTestConfiguration {

        @Bean
        CatalogFixtures CatalogFixtures(CategoryRepository categoryRepository, ProductRepository productRepository, SkuRepository skuRepository) {
            return new CatalogFixtures(categoryRepository, productRepository, skuRepository);
        }

    }
    
}