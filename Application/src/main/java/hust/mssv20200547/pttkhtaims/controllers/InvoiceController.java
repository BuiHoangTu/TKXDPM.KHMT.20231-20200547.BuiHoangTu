package hust.mssv20200547.pttkhtaims.controllers;

import hust.mssv20200547.pttkhtaims.AIMS;
import hust.mssv20200547.pttkhtaims.models.DeliveryInfo;
import hust.mssv20200547.pttkhtaims.models.Invoice;
import hust.mssv20200547.pttkhtaims.models.Order;
import hust.mssv20200547.pttkhtaims.services.IPlaceOrderService;
import hust.mssv20200547.pttkhtaims.services.PlaceOrderService;
import hust.mssv20200547.pttkhtaims.subsystem.bank.models.PaymentTransaction;
import hust.mssv20200547.pttkhtaims.subsystem.bank.vnpay.VnPay;
import hust.mssv20200547.pttkhtaims.views.MediaInVerticalView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class InvoiceController implements Initializable {
    @FXML
    private Label pageTitle;
    @FXML
    private Label labelSubTotal;
    @FXML
    private Label labelShipFee;
    @FXML
    private Label labelPhone;
    @FXML
    private Label labelTotal;
    @FXML
    private Label labelName;
    @FXML
    private Label labelCity;
    @FXML
    private Label labelAddress;
    @FXML
    private Label labelShipInstruction;
    @FXML
    private ScrollPane vboxIte;
    @FXML
    private VBox vboxItems;

    private Invoice invoice;
    private IPlaceOrderService placeOrderService = new PlaceOrderService();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setDefaultValues(DeliveryInfo deliveryInfo, Invoice invoice) throws IOException {
        this.invoice = invoice;

        this.labelAddress.setText(deliveryInfo.getDetailedAddress());
        this.labelName.setText(deliveryInfo.getReceiver());
        this.labelCity.setText(deliveryInfo.getCityAddress());
        this.labelPhone.setText(deliveryInfo.getPhoneNumber());
        this.labelShipInstruction.setText(deliveryInfo.getInstruction());

        long subTotal = AIMS.cart.totalPrice();
        this.labelSubTotal.setText(String.valueOf(subTotal));

        Order order = new Order(AIMS.cart, deliveryInfo);
        long deliveryFee = placeOrderService.calculateDeliveryFee(order);
        this.labelShipFee.setText(String.valueOf(deliveryFee));

        this.labelTotal.setText(String.valueOf(subTotal + deliveryFee));

        // setup view
        List<Node> itemViews = vboxItems.getChildren();
        itemViews.clear();

        for (var mediaEntry : order.getMediaInOrder().entrySet()) {
            var mediaView = new MediaInVerticalView();
            var mediaController = mediaView.getController();
            mediaController.setDefaultValues(mediaEntry);
            itemViews.add(mediaView.getRoot());
        }
    }

    @FXML
    public void confirmInvoice() {
        // wait for result
        var root = this.labelAddress.getScene().getRoot();
        root.setDisable(true);
        PaymentTransaction res = new VnPay().makePaymentTransaction(invoice, "Pay for AIMS");
        root.setDisable(false);

        // TODO: set Payment info in db

    }
}
