package hust.mssv20200547.pttkhtaims.services;

public class PlaceOrderService implements IPlaceOrderService {
    @Override
    public boolean validatePhoneNumber(String phoneNumber) {
        return false;
    }

    @Override
    public boolean validateAddress(String address) {
        return false;
    }
}
