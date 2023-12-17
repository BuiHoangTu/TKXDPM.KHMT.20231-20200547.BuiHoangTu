package hust.mssv20200547.pttkhtaims.subsystem.bank;

import hust.mssv20200547.pttkhtaims.subsystem.bank.models.Invoice;
import hust.mssv20200547.pttkhtaims.subsystem.bank.models.PaymentTransaction;

public interface IBank {
    PaymentTransaction makePaymentTransaction(Invoice invoice, String contents);
}
