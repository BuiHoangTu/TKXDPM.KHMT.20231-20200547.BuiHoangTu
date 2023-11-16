package hust.mssv20200547.pttkhtaims.services;

public class PayOrderService implements IPayOrderService {
    @Override
    public boolean validateCardInfo(String cardHolderName, String cardNumber, String expirationDate, String securityCode) throws IllegalArgumentException {
        return false;
    }
}
