package hust.mssv20200547.pttkhtaims.models;

import lombok.Getter;

@Getter
public class Card {
    private static final int CARD_HOLDER_NAME_MAX_LENGTH = 50;
    private static final int CARD_NUMBER_LENGTH = 16;
    private static final int SECURITY_CODE_LENGTH = 3;

    private final String cardHolderName;
    private final char[] cardNumber;
    private final char[] expirationDate;
    private final char[] securityCode;

    private Card(String cardHolderName, char[] cardNumber, char[] expirationDate, char[] securityCode) {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
    }
}
