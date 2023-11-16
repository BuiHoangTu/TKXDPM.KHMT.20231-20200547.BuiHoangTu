package hust.mssv20200547.pttkhtaims.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PayOrderServiceTest {
    private IPayOrderService payOrderService;

    @BeforeEach
    void setUp() {
        this.payOrderService = new PayOrderService();
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/hust/mssv20200547/pttkhtaims/services/validate-card.csv"})
    void validateCardInfo(
            String cardHolderName,
            String cardNumber,
            String expirationDate,
            String securityCode,
            boolean expectedIsSuccess,
            String expectedErrorMessage) {

        try {
            boolean isSuccess = payOrderService.validateCardInfo(cardHolderName, cardNumber, expirationDate, securityCode);
            assertEquals(expectedIsSuccess, isSuccess);
        } catch (Exception e) {
            assertFalse(expectedIsSuccess);
            assertEquals(expectedErrorMessage, e.getMessage());
        }
    }
}
