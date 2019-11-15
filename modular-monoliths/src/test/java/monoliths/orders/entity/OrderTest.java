package monoliths.orders.entity;

import monoliths.commons.model.OrderSheet;
import monoliths.orders.domain.entity.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;

import static monoliths.orders.Fixtures.builderOrderSheet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderTest {

    @Mock OrderProductMapper orderProductMapper;
    @Mock ShippingDesk shippingDesk;

    @Test
    void 주문_및_총_주문금액_계산() {
        when(orderProductMapper.mapFrom(any())).thenReturn(Arrays.asList(
                OrderProduct.builder().quantity(1).price(BigDecimal.valueOf(300)).build(),
                OrderProduct.builder().quantity(2).price(BigDecimal.valueOf(500)).build()
        ));

        Order order = placeOrder(orderProductMapper);

        assertEquals(order.getOrderStatus(), OrderStatus.ORDERED);
        assertEquals(order.calculateTotalPrice(), BigDecimal.valueOf(1300));
    }

    @Test
    void 결제됨() {
        Order order = placeOrder(orderProductMapper);
        assertEquals(order.payed(shippingDesk).getOrderStatus(), OrderStatus.PAYED);
    }

    private Order placeOrder(OrderProductMapper orderProductMapper) {
        OrderSheet orderSheet = builderOrderSheet().build();
        return Order.place(orderSheet, orderProductMapper, shippingDesk);
    }

}