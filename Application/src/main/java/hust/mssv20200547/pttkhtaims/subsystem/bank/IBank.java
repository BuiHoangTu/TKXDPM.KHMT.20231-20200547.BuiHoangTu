package hust.mssv20200547.pttkhtaims.subsystem.bank;

import hust.mssv20200547.pttkhtaims.subsystem.bank.exceptions.pay.*;

public interface IBank {
    IPaymentTransaction makePaymentTransaction(
            IInvoice invoice,
            String contents
    ) throws
            PaymentException;
}
