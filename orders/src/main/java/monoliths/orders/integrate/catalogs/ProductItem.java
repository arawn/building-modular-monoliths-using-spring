package monoliths.orders.integrate.catalogs;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.PRIVATE)
public class ProductItem {

    private UUID id;
    private String name;
    private BigDecimal price;
    
    private UUID skuId;
    
}