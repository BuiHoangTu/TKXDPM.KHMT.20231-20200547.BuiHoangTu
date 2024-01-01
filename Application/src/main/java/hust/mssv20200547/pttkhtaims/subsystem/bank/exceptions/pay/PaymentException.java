package hust.mssv20200547.pttkhtaims.subsystem.bank.exceptions.pay;

import hust.mssv20200547.pttkhtaims.subsystem.bank.models.PaymentTransaction;

/**
 * This exception is thrown if pay status is not success
 */
public abstract class PaymentException extends Exception {
    public PaymentException(String message) {
        super(message);
    }
}
