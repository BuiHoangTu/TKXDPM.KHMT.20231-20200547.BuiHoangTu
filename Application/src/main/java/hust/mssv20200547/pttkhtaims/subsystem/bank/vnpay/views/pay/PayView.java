package hust.mssv20200547.pttkhtaims.subsystem.bank.vnpay.views.pay;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.web.WebView;

public class PayView {
    public PayView(String payUrl) {
        Stage stage = new Stage();
        WebView webView = new WebView();
        webView.getEngine().load(payUrl);
        stage.setScene(new Scene(webView));
        stage.show();
    }
}
