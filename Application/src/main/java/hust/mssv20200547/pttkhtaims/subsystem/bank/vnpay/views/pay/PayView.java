package hust.mssv20200547.pttkhtaims.subsystem.bank.vnpay.views.pay;

import hust.mssv20200547.pttkhtaims.subsystem.bank.models.PaymentTransaction;
import hust.mssv20200547.pttkhtaims.subsystem.bank.vnpay.VnPayConfig;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;
import javafx.scene.web.WebView;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PayView {
    private PaymentTransaction transaction = null;
    private final Stage vnPayStage;

    public PayView(String payUrl) {
        WebView webView = new WebView();
        WebEngine engine = webView.getEngine();
        engine.load(payUrl);

        var scene = new Scene(webView);

        this.vnPayStage = new Stage();
        vnPayStage.setScene(scene);

        // parse data when page changed
        engine.locationProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.contains(VnPayConfig.RETURN_URL)) {
                return;
            }
            try {
                URI uri = new URI(newValue);
                String query = uri.getQuery();

                Map<String, String> params = this.parseQuery(query);

                // checksum
                String hash = params.get("vnp_SecureHash");
                params.remove("vnp_SecureHashType");
                params.remove("vnp_SecureHash");

                String signed = VnPayConfig.hashAllFields(params);
                if (!signed.equals(hash)) {
                    System.out.println("Invalid checksum");
                    return;
                }

                // get data
                String errorCode = params.get("vnp_TransactionStatus");
                String transactionId = params.get("vnp_TransactionNo");
                String transactionContent = params.get("vnp_OrderInfo");
                int amount = Integer.parseInt((String) params.get("vnp_Amount")) / 100;

                String createdAt = params.get("vnp_PayDate");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                Date date = dateFormat.parse(createdAt);

                this.transaction = new PaymentTransaction(errorCode, transactionId, transactionContent, amount, date);

                vnPayStage.close();
            } catch (URISyntaxException | ParseException e) {
                // shouldnt have error
                System.out.println(e.toString());
            }
        });
    }

    public PaymentTransaction showThenGetPaymentTransaction() {
        vnPayStage.showAndWait();
        return this.transaction;
    }

    private Map<String, String> parseQuery(String query) {
        HashMap<String, String> map = new HashMap<>();

        if (query != null && !query.isEmpty()) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    map.put(keyValue[0], keyValue[1]);
                }
            }
        }

        return map;
    }
}
