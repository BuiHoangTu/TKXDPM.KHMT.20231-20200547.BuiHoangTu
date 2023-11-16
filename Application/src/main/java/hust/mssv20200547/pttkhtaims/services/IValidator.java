package hust.mssv20200547.pttkhtaims.services;

import hust.mssv20200547.pttkhtaims.models.Card;
import lombok.NonNull;

public interface IValidator {
    boolean validatePhoneNumber(String phoneNumber);

    boolean validateAddress(String address);

    @NonNull
    Card validateCartInfo(String cardHolderName, String cardNumber, String expirationDate, String securityCode) throws IllegalArgumentException;

}
