package hust.mssv20200547.pttkhtaims.subsystem.bank.models;

import lombok.Getter;

import java.util.Date;

@Getter
public class PaymentTransaction {
    private String transactionId;
    private String errorCode;
    private String transactionContent;
    private int amount;
    private Integer orderID;
    private Date createdAt;

    public PaymentTransaction(String errorCode, String transactionId, String transactionContent,
                              int amount, Date createdAt) {
        this.errorCode = errorCode;


        this.transactionId = transactionId;
        this.transactionContent = transactionContent;
        this.amount = amount;
        this.createdAt = createdAt;
    }


    public String getTransactionContent() {
        return transactionContent;
    }

}
