package monoliths.orders.integrate.catalogs;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.PRIVATE)
public class Product {

    private UUID id;
    private String code;
    private String name;
    private BigDecimal price;
    private Set<ProductItem> items = new HashSet<>();

}