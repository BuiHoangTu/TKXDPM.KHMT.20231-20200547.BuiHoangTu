package hust.mssv20200547.pttkhtaims.database.mysql;

import hust.mssv20200547.pttkhtaims.database.IOrderSource;
import hust.mssv20200547.pttkhtaims.models.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderSource extends MysqlBase implements IOrderSource {
    @Override
    public int saveOrder(int paymentInfoId, int deliveryInfoId, Order order) throws SQLException {
        var mysql = getConnection();

        // save to aims order
        var prepareStm = mysql.prepareStatement(
                "INSERT INTO aims_order(paymentInfoId, deliveryInfoId, orderStatus) " +
                        "values (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        );

        prepareStm.setInt(1, paymentInfoId);
        prepareStm.setLong(2, deliveryInfoId);
        // not paid
        prepareStm.setLong(3, 1);

        int affectedRow = prepareStm.executeUpdate();

        if (affectedRow <= 0) {
            throw new SQLException("Insert failed");
        }

        try (ResultSet generatedKeys = prepareStm.getGeneratedKeys()) {
            if (!generatedKeys.next()) {
                throw new SQLException("No ID obtained.");
            } else {
                int orderId = (generatedKeys.getInt(1));
                order.setOrderId(orderId);

                if (!order.getMediaInOrder().isEmpty()) {
                    var prepareStmMIO = mysql.prepareStatement(
                            "INSERT INTO medias_in_order(order_id, media_id, quantity) " +
                                    "values (?, ?, ?)"
                    );
                    for (var entry : order.getMediaInOrder().entrySet()) {
                        var media = entry.getKey();
                        prepareStmMIO.setInt(1, orderId);
                        prepareStmMIO.setLong(2, media.getId());
                        prepareStmMIO.setLong(3, entry.getValue());
                        prepareStmMIO.addBatch();
                    }

                    prepareStmMIO.executeBatch();
                }

                return orderId;
            }
        }
    }

    @Override
    public void setOrderStatus(int orderId, Order.OrderStatus status) throws SQLException {
        var mysql = getConnection();
        var prepareStm = mysql.prepareStatement(
                "UPDATE aims_order " +
                        "SET orderStatus = ? " +
                        "where id = ?"
        );

        prepareStm.setInt(1, status.getI());
        prepareStm.setInt(2, orderId);

        prepareStm.executeUpdate();
    }

}
