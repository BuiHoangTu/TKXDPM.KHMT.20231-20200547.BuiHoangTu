package hust.mssv20200547.pttkhtaims.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Invoice {
    private int orderId;
    private final int priceNoVat;
    private final int priceWithVat;
    private final int deliveryFee;
    private final int totalFee;

    public Invoice(int priceNoVat, int priceWithVat, int deliveryFee, int totalFee) {
        this.priceNoVat = priceNoVat;
        this.priceWithVat = priceWithVat;
        this.deliveryFee = deliveryFee;
        this.totalFee = totalFee;
    }

    public Invoice(int priceNoVat, int deliveryFee) {
        this.priceNoVat = priceNoVat;
        this.priceWithVat = priceNoVat * 10/100;
        this.deliveryFee = deliveryFee;
        this.totalFee = priceWithVat + deliveryFee;
    }
}
