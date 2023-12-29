package hust.mssv20200547.pttkhtaims.database;

import hust.mssv20200547.pttkhtaims.models.Invoice;

public interface IInvoiceSource {
    void saveInvoice(Invoice invoice);
}
