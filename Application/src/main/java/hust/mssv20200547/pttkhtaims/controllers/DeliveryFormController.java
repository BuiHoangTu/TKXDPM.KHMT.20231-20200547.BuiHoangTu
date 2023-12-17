package hust.mssv20200547.pttkhtaims.controllers;

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

    @FXML
    private void goBackPage() {
        view.apply((Stage) radioFastDelivery.getScene().getWindow());
    }
    @FXML
    private void updateDeliveryMethodInfo() {
        // TODO: save to invoice,

    }

    public void setPrevPage(BaseView view) {
        this.view = view;
    }

}
