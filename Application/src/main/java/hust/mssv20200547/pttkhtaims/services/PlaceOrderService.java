package hust.mssv20200547.pttkhtaims.services;

import hust.mssv20200547.pttkhtaims.database.IDatabase;
import hust.mssv20200547.pttkhtaims.models.Cart;
import hust.mssv20200547.pttkhtaims.models.Media;

import java.util.Collections;
import java.util.List;

public class PlaceOrderService implements IPlaceOrderService {
    @Override
    public boolean validatePhoneNumber(String phoneNumber) {
        return false;
    }

    @Override
    public List<Media> validateProductQuantity(IDatabase database, Cart cart) {
        return Collections.emptyList();
    }

}
