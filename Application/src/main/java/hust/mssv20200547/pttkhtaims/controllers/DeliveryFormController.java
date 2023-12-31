package hust.mssv20200547.pttkhtaims.controllers;

import hust.mssv20200547.pttkhtaims.AIMS;
import hust.mssv20200547.pttkhtaims.database.IDeliveryInfoSource;
import hust.mssv20200547.pttkhtaims.database.IInvoiceSource;
import hust.mssv20200547.pttkhtaims.database.IOrderSource;
import hust.mssv20200547.pttkhtaims.database.IPaymentInfoSource;
import hust.mssv20200547.pttkhtaims.database.mysql.DeliveryInfoSource;
import hust.mssv20200547.pttkhtaims.database.mysql.InvoiceSource;
import hust.mssv20200547.pttkhtaims.database.mysql.OrderSource;
import hust.mssv20200547.pttkhtaims.database.mysql.PaymentInfoSource;
import hust.mssv20200547.pttkhtaims.models.DeliveryInfo;
import hust.mssv20200547.pttkhtaims.models.Order;
import hust.mssv20200547.pttkhtaims.services.IPlaceOrderService;
import hust.mssv20200547.pttkhtaims.services.PlaceOrderService;
import hust.mssv20200547.pttkhtaims.models.Invoice;
import hust.mssv20200547.pttkhtaims.views.BaseView;
import hust.mssv20200547.pttkhtaims.views.InvoiceView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeliveryFormController implements Initializable {
    private BaseView view;

    @FXML
    private TextField tfReceiver;
    @FXML
    private Label errorName;
    @FXML
    private TextField tfPhoneNumber;
    @FXML
    private Label errorNumber;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfCity;
    @FXML
    private TextField tfDetailAddress;
    @FXML
    private TextField tfInstruction;
    @FXML
    private RadioButton radioNormalDelivery;
    @FXML
    private ToggleGroup radioGroupDeliveryType;
    @FXML
    private RadioButton radioFastDelivery;
    @FXML
    private DatePicker deliveryTime;
    @FXML
    private Label errorProvince;

    private IPlaceOrderService placeOrderService = new PlaceOrderService();
    private IDeliveryInfoSource deliveryInfoSource = new DeliveryInfoSource();
    private IOrderSource orderSource = new OrderSource();
    private IPaymentInfoSource paymentInfoSource = new PaymentInfoSource();
    private IInvoiceSource invoiceSource = new InvoiceSource();

    @FXML
    private void goBackPage() {
        view.apply((Stage) radioFastDelivery.getScene().getWindow());
    }
    @FXML
    private void updateDeliveryMethodInfo() throws IOException, SQLException {
        var receiver = this.tfReceiver.getText();
        var phone = this.tfPhoneNumber.getText();
        var email = this.tfEmail.getText();
        var city = this.tfCity.getText();
        var detailAddr = this.tfDetailAddress.getText();
        var ins = this.tfInstruction.getText();

        if (! placeOrderService.validateName(receiver)) return;
        if (! placeOrderService.validatePhoneNumber(phone)) return;

        DeliveryInfo deliveryInfo = new DeliveryInfo(receiver, phone, email, city, detailAddr, ins);
        long totalPrice = AIMS.cart.totalPrice();
        Order order = new Order(AIMS.cart, deliveryInfo);

        // create in db
        int deliveryId = this.deliveryInfoSource.saveDeliveryInfo(deliveryInfo);
        int paymentId = this.paymentInfoSource.createHolder();
        int orderId = this.orderSource.saveOrder(paymentId, deliveryId);

        long deliveryFee = placeOrderService.calculateDeliveryFee(order);
        Invoice invoice = new Invoice(totalPrice, deliveryFee);
        invoice.setOrderId(orderId);

        // TODO: save to db
        this.invoiceSource.saveInvoice(invoice);

        var invoiceView = new InvoiceView();
        invoiceView.getController().setDefaultValues(deliveryInfo, invoice);
        invoiceView.apply((Stage) tfReceiver.getScene().getWindow());
    }

    public void setPrevPage(BaseView view) {
        this.view = view;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tfReceiver.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                var res = placeOrderService.validateName(this.tfReceiver.getText());
                errorName.setVisible(!res);
            }
        });

        this.tfPhoneNumber.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                var res = placeOrderService.validatePhoneNumber(this.tfPhoneNumber.getText());
                errorNumber.setVisible(!res);
            }
        });
    }
}
