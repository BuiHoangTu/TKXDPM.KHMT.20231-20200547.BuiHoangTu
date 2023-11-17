package hust.mssv20200547.pttkhtaims.services;

public class PayOrderService implements IPayOrderService {

    @Override
    public boolean validateCardHolderName(String cardHolderName) {
        return false;
    }

    @Override
    public boolean validateCardNumber(String cardNumber) {
        return false;
    }

    @Override
    public boolean validateCardExpirationDate(String expirationDate) {
        return false;
    }

    @Override
    public boolean validateCardSecurityCode(String securityCode) {
        return false;
    }
}
