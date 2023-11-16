package hust.mssv20200547.pttkhtaims.services;

import hust.mssv20200547.pttkhtaims.models.Card;

public class Validator implements IValidator {
    @Override
    public boolean validatePhoneNumber(String phoneNumber) {
        return false;
    }

    @Override
    public boolean validateAddress(String address) {
        return false;
    }

    @Override
    public Card validateCartInfo(String cardHolderName, String cardNumber, String expirationDate, String securityCode) {
        throw new IllegalArgumentException();
    }
}
