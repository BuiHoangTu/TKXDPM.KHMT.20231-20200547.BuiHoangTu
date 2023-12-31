package hust.mssv20200547.pttkhtaims.database;

import hust.mssv20200547.pttkhtaims.views.PaymentInfo;

import java.sql.SQLException;

public interface IPaymentInfoSource {
    int createHolder() throws SQLException;
    void savePaymentInfo(PaymentInfo paymentInfo) throws SQLException;
}
