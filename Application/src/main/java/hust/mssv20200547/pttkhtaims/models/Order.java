package hust.mssv20200547.pttkhtaims.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@AllArgsConstructor
@Getter
@Setter
public class Order {
    public enum OrderStatus {
        NOT_PAID (1),
        PAID_SHIPPING (2),
        DELIVERED (3),
        CANCELED (4);

        @Getter
        private final int i;
        private OrderStatus(int i) {
            this.i = i;
        }
    }

    private int orderId;
    private final Map<Media, Long> mediaInOrder = new HashMap<>();
    private DeliveryInfo deliveryInfo;
    private OrderStatus orderStatus;

    public Order(Cart currentCart, DeliveryInfo deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
        mediaInOrder.putAll(currentCart);
    }
}
