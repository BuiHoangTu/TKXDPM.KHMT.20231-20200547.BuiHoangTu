package hust.mssv20200547.pttkhtaims.subsystem.bank.exceptions.pay;

import java.io.Serial;

/**
 * Error 07
 */
public class AnonymousTransactionException extends PaymentException {
    public AnonymousTransactionException() {
        super("ERROR: Giao dịch bị nghi ngờ gian lận");
    }
}
