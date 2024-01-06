package hust.mssv20200547.pttkhtaims.subsystem.bank.implement.vnpay.models;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @param createdAt private Integer orderID;
 */
@Getter
public record PaymentTransaction(
        String errorCode,
        String transactionId,
        String transactionContent,
        int amount,
        LocalDateTime createdAt
) implements hust.mssv20200547.pttkhtaims.subsystem.bank.IPaymentTransaction {


}
