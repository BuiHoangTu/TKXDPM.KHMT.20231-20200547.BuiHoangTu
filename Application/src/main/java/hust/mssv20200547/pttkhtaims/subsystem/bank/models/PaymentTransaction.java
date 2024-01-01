package hust.mssv20200547.pttkhtaims.subsystem.bank.models;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PaymentTransaction {
    private final String transactionId;
    private final String errorCode;
    private final String transactionContent;
    private final int amount;
    //    private Integer orderID;
    private final LocalDateTime createdAt;

    public PaymentTransaction(
            String errorCode,
            String transactionId,
            String transactionContent,
            int amount,
            LocalDateTime createdAt) {
        this.errorCode = errorCode;
        this.transactionId = transactionId;
        this.transactionContent = transactionContent;
        this.amount = amount;
        this.createdAt = createdAt;
    }


}
