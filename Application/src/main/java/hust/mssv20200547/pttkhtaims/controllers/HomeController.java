package hust.mssv20200547.pttkhtaims.controllers;

import hust.mssv20200547.pttkhtaims.AIMS;
import hust.mssv20200547.pttkhtaims.models.Media;
import hust.mssv20200547.pttkhtaims.services.IStoreService;
import hust.mssv20200547.pttkhtaims.services.StoreService;
import hust.mssv20200547.pttkhtaims.views.CartView;
import hust.mssv20200547.pttkhtaims.views.MediaInSquareView;
import javafx.event.ActionEvent;
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
import java.util.Map;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    private static final int MAX_MEDIAS_VIEW = 20;

    private final IStoreService storeService = new StoreService();

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

        this.setDefaultValues(this.storeService.recommendedMedias(MAX_MEDIAS_VIEW));
    }

    private void setDefaultValues(Map<Media, Long> medias) {
        var mediaEntries = medias.entrySet().stream().toList();
        var vBoxes = new VBoxNext(vBoxMedia1, vBoxMedia2, vBoxMedia3, vBoxMedia4);
        vBoxes.clearAll();

        for (var mediaEntry : mediaEntries) {
            try {
                var mediaView = new MediaInSquareView();
                MediaInSquareController mediaController = mediaView.getController();
                mediaController.setDefaultValues(mediaEntry);
                vBoxes.next().getChildren().add(mediaView.getRoot());
            } catch (IOException e) {
                continue;
            }
        }
    }

    @FXML
    private void searchMedias(ActionEvent ignoredEvent) {
        var selected = this.searchType.getSelectedToggle();

        if (selected == this.radioMenuItemTitle) {
            this.setDefaultValues(storeService.searchMediaTitleInStore(this.textFieldSearch.getText(), MAX_MEDIAS_VIEW));
        }

        if (selected == this.radioMenuItemCategory) {
            this.setDefaultValues(storeService.searchMediaCategoryInStore(this.textFieldSearch.getText(), MAX_MEDIAS_VIEW));
        }
    }

    @FXML
    public void openCart(MouseEvent ignoredMouseEvent) throws IOException {
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
