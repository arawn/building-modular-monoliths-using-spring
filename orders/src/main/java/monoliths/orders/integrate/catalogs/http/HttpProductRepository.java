package monoliths.orders.integrate.catalogs.http;

import java.util.UUID;

import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import monoliths.orders.integrate.catalogs.Product;
import monoliths.orders.integrate.catalogs.ProductRepository;

@AllArgsConstructor
class HttpProductRepository implements ProductRepository {

    private RestTemplate restTemplate;

    @Override
    public Product getProduct(UUID productId) {
        return restTemplate.getForObject("http://catalogs/product/find?productId={productId}", Product.class, productId);
    }
    
}