package monoliths.orders.entity;

import monoliths.orders.domain.entity.OrderProduct;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderProductTest {

    @Test
    void 최소_주문수량_확인() {
        assertEquals(checkoutOrderProduct(500, 1).getQuantity(), 1);
        assertThrows(OrderProduct.IncorrectOrderProductQuantityException.class, () -> {
            checkoutOrderProduct(500, 0);
        });
    }

    @Test
    void 주문금액_계산() {
        assertEquals(calculatePrice(500, 1), BigDecimal.valueOf(500));
        assertEquals(calculatePrice(250, 2), BigDecimal.valueOf(500));
    }

    private OrderProduct checkoutOrderProduct(long price, long quantity) {
        return OrderProduct.builder().price(BigDecimal.valueOf(price)).quantity(quantity).build();
    }

    private BigDecimal calculatePrice(long price, long quantity) {
        return checkoutOrderProduct(price, quantity).calculatePrice();
    }

}