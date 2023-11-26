package hust.mssv20200547.pttkhtaims.controllers;

import hust.mssv20200547.pttkhtaims.models.Media;
import hust.mssv20200547.pttkhtaims.views.MediaInHomeView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setMedias(Map<Media, Long> medias) throws IOException {
        var mediaEntries = medias.entrySet().stream().toList();
        var vBoxes = new VBoxNext(vBoxMedia1, vBoxMedia2, vBoxMedia3, vBoxMedia4);
        vBoxes.clearAll();

        for (var mediaEntry : mediaEntries) {
            var mediaView = new MediaInHomeView();
            MediaInHomeController mediaController = mediaView.getController();
            mediaController.setMedia(mediaEntry);
            vBoxes.next().getChildren().add(mediaView.getRoot());
        }
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
