package hust.mssv20200547.pttkhtaims.controllers;

import hust.mssv20200547.pttkhtaims.AIMS;
import hust.mssv20200547.pttkhtaims.database.IMediaSource;
import hust.mssv20200547.pttkhtaims.database.mysql.MediaSourceMySql;
import hust.mssv20200547.pttkhtaims.models.Media;
import hust.mssv20200547.pttkhtaims.views.CartView;
import hust.mssv20200547.pttkhtaims.views.MediaInSquareView;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    private static final IMediaSource MYSQL = new MediaSourceMySql();

    @FXML
    private RadioMenuItem radioMenuItemTitle;
    @FXML
    private ToggleGroup searchType;
    @FXML
    private RadioMenuItem radioMenuItemCategory;
    @FXML
    private ImageView aimsImage;
    @FXML
    private SplitMenuButton splitMenuBtnSearch;
    @FXML
    private ImageView cartImage;
    @FXML
    private Label numMediaInCart;
    @FXML
    private HBox hBoxMedia;
    @FXML
    private VBox vBoxMedia1;
    @FXML
    private VBox vBoxMedia2;
    @FXML
    private VBox vBoxMedia3;
    @FXML
    private VBox vBoxMedia4;
    @FXML
    private TextField textFieldSearch;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.numMediaInCart.setText(String.valueOf(0));

        this.cartImage.addEventHandler(EventType.ROOT, event -> {

        });
    }

    public void setDefaultValues(Map<Media, Long> medias) throws IOException {
        var mediaEntries = medias.entrySet().stream().toList();
        var vBoxes = new VBoxNext(vBoxMedia1, vBoxMedia2, vBoxMedia3, vBoxMedia4);
        vBoxes.clearAll();

        for (var mediaEntry : mediaEntries) {
            var mediaView = new MediaInSquareView();
            MediaInSquareController mediaController = mediaView.getController();
            mediaController.setDefaultValues(mediaEntry);
            vBoxes.next().getChildren().add(mediaView.getRoot());
        }
    }

    @FXML
    private void searchMedias(ActionEvent ignoredEvent) throws SQLException, IOException {
        var selected = this.searchType.getSelectedToggle();

        if (selected == this.radioMenuItemTitle) {
            this.setDefaultValues(MYSQL.searchMedias("title", this.textFieldSearch.getText(), 20));
        }

        if (selected == this.radioMenuItemCategory) {
            this.setDefaultValues(MYSQL.searchMedias("category", this.textFieldSearch.getText(), 20));
        }
    }

    @FXML
    public void openCart(MouseEvent mouseEvent) throws IOException {
        var cartView = new CartView();

        cartView.apply((Stage) cartImage.getScene().getWindow());

        cartView.getController().setDefaultValues(AIMS.cart);
    }


    private static class VBoxNext {
        private final VBox[] vBoxes;
        private final int count;
        private int index;

        private VBoxNext(VBox... vBoxes) {
            this.vBoxes = vBoxes;
            this.index = -1;
            this.count = vBoxes.length;
        }

        public VBox next() {
            this.index = (this.index + 1) % count;
            return vBoxes[index];
        }

        public void clearAll() {
            for (var each : this.vBoxes) {
                each.getChildren().clear();
            }
        }


    }
}
