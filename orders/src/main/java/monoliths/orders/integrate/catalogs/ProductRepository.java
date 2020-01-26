package monoliths.orders.integrate.catalogs;

import java.util.UUID;

public interface ProductRepository {

    Product getProduct(UUID productId);

}