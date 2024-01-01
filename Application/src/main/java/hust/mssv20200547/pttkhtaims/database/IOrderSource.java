package hust.mssv20200547.pttkhtaims.database;

import hust.mssv20200547.pttkhtaims.models.Order;

import java.sql.SQLException;

public interface IOrderSource {
    int saveOrder(int paymentInfoId, int deliveryInfoId, Order order) throws SQLException;
    void setOrderStatus (int orderId, Order.OrderStatus status) throws SQLException;
}
