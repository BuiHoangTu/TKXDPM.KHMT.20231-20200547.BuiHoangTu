package hust.mssv20200547.pttkhtaims.database.mysql;

import hust.mssv20200547.pttkhtaims.database.IInvoiceSource;
import hust.mssv20200547.pttkhtaims.models.Invoice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InvoiceSource extends MysqlBase implements IInvoiceSource {
    @Override
    public int saveInvoice(Invoice invoice) throws SQLException {
        var mysql = getConnection();
        var prepareStm = mysql.prepareStatement(
                "INSERT INTO invoice(orderid, priceNoVAT, priceWithVAT, deliveryFee, totalFee) " +
                        "values (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        );

        prepareStm.setInt(1, invoice.getOrderId());
        prepareStm.setLong(2, invoice.getPriceNoVat());
        prepareStm.setLong(3, invoice.getPriceWithVat());
        prepareStm.setLong(4, invoice.getDeliveryFee());
        prepareStm.setLong(5, invoice.getTotalFee());

        int res = prepareStm.executeUpdate();

        if (res <= 0) throw new SQLException("Insert failed");

        try (ResultSet generatedKeys = prepareStm.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return (generatedKeys.getInt(1));
            }
            else {
                throw new SQLException("No ID obtained.");
            }
        }
    }
}
