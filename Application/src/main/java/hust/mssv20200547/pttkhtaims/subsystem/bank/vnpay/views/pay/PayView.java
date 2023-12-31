package hust.mssv20200547.pttkhtaims.subsystem.bank.vnpay.views.pay;

import hust.mssv20200547.pttkhtaims.subsystem.bank.vnpay.VnPayConfig;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;
import javafx.scene.web.WebView;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class PayView {
    public PayView(String payUrl) {
        WebView webView = new WebView();
        WebEngine engine = webView.getEngine();
        engine.load(payUrl);

        // close when redirected
        engine.locationProperty().addListener((observable, oldValue, newValue) -> {
            this.handleUrlChanged(newValue); // Xử lý khi URL thay đổi
        });

        var scene = new Scene(webView);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private void handleUrlChanged(String strUrl) {
        if (strUrl.contains(VnPayConfig.RETURN_URL)) {
            try {
                URI uri = new URI(strUrl);
                String query = uri.getQuery();

                Map<String, String> params = this.parseQuery(query);


            } catch (URISyntaxException e) {
                System.out.println(e.toString());
            }
        }
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
