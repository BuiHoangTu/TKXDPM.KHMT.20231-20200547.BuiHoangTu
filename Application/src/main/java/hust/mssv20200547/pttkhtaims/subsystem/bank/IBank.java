package hust.mssv20200547.pttkhtaims.subsystem.bank;

import hust.mssv20200547.pttkhtaims.subsystem.bank.exceptions.pay.*;
import hust.mssv20200547.pttkhtaims.subsystem.bank.models.PaymentTransaction;

public interface IBank {
    PaymentTransaction makePaymentTransaction(
            IInvoice invoice,
            String contents
    ) throws
            AnonymousTransactionException,
            ClientBankException,
            TransactionFailedException,
            TransactionNotDoneException,
            TransactionReverseException,
            UnrecognizedException,
            PaymentException;
}
