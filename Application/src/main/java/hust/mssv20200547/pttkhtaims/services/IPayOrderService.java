package hust.mssv20200547.pttkhtaims.services;

public interface IPayOrderService {
    /**
     * Check if the information can be the information on a credit card
     * @param cardHolderName cardholder's name
     * @param cardNumber the 16 digits on the card
     * @param expirationDate expiration date, must in format mm/yy
     * @param securityCode the 3 digits code on the card
     * @return true if the information can be the information on a credit card
     * @throws IllegalArgumentException if the information can't be the information on a credit card
     */
    boolean validateCardInfo(String cardHolderName, String cardNumber, String expirationDate, String securityCode) throws IllegalArgumentException;

}
