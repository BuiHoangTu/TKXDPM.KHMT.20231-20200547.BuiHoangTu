package hust.mssv20200547.pttkhtaims.controllers;

import hust.mssv20200547.pttkhtaims.AIMS;
import hust.mssv20200547.pttkhtaims.models.DeliveryInfo;
import hust.mssv20200547.pttkhtaims.models.Order;
import hust.mssv20200547.pttkhtaims.services.IPlaceOrderService;
import hust.mssv20200547.pttkhtaims.services.PlaceOrderService;
import hust.mssv20200547.pttkhtaims.subsystem.bank.models.Invoice;
import hust.mssv20200547.pttkhtaims.subsystem.bank.vnpay.VnPay;
import hust.mssv20200547.pttkhtaims.subsystem.bank.vnpay.views.pay.PayView;
import hust.mssv20200547.pttkhtaims.views.BaseView;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class DeliveryFormController {
    private BaseView view;

    @FXML
    private RadioButton radioNormalDelivery;
    @FXML
    private ToggleGroup radioGroupDeliveryType;
    @FXML
    private RadioButton radioFastDelivery;
    @FXML
    private TextField shipmentDetail;
    @FXML
    private TextField deliveryInstruction;
    @FXML
    private DatePicker deliveryTime;
    @FXML
    private Label errorProvince;

    private IPlaceOrderService placeOrderService = new PlaceOrderService();

    @FXML
    private void goBackPage() {
        view.apply((Stage) radioFastDelivery.getScene().getWindow());
    }
    @FXML
    private void updateDeliveryMethodInfo() {
        // TODO: save to invoice,
        // TODO: switch to invoice screen first
        DeliveryInfo deliveryInfo = new DeliveryInfo(null, null, null, null, null);
        long totalPrice = AIMS.cart.totalPrice();
        Order order = new Order(AIMS.cart, deliveryInfo);
        long deliveryFee = placeOrderService.calculateDeliveryFee(order);
        Invoice invoice = new Invoice(totalPrice, deliveryFee);
        // TODO: save to db
        // TODO: put this in suitable class
        var res = new VnPay().makePaymentTransaction(invoice, "Pay for AIMS");
        // TODO: go to suitable class
    }

    public void setPrevPage(BaseView view) {
        this.view = view;
    }

}
