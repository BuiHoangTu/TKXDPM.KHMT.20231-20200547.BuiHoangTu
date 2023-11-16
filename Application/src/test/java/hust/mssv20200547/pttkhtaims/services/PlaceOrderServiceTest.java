package hust.mssv20200547.pttkhtaims.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlaceOrderServiceTest {
    private IPlaceOrderService placeOrderService;

    @BeforeEach
    void setUp() {
        this.placeOrderService = new PlaceOrderService();
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/hust/mssv20200547/pttkhtaims/services/validate-phone.csv"})
    void validatePhoneNumber(String phoneNumber, boolean expectedRes) {
        var res = this.placeOrderService.validatePhoneNumber(phoneNumber);
        assertEquals(expectedRes, res);
    }


}