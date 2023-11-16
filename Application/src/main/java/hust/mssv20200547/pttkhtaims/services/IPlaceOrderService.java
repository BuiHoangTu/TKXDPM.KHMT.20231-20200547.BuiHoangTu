package hust.mssv20200547.pttkhtaims.services;

public interface IPlaceOrderService {
    /**
     * Check if the phone number is in right format
     * @param phoneNumber the phone number
     * @return true if it is in right format
     * <br/> false if it is not
     */
    boolean validatePhoneNumber(String phoneNumber);

    /**
     *
     * @param address
     * @return
     */
    boolean validateAddress(String address);
}
