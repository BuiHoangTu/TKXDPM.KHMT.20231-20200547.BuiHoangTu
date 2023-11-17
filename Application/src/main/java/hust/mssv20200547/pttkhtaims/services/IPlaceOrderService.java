package hust.mssv20200547.pttkhtaims.services;

import hust.mssv20200547.pttkhtaims.database.IDatabase;
import hust.mssv20200547.pttkhtaims.models.Cart;
import hust.mssv20200547.pttkhtaims.models.Media;

import java.util.List;

public interface IPlaceOrderService {
    /**
     * Check if the phone number is in right format
     * @param phoneNumber the phone number
     * @return true if it is in right format
     * <br/> false if it is not
     */
    boolean validatePhoneNumber(String phoneNumber);

    List<Media> validateProductQuantity(IDatabase database, Cart cart);

}
