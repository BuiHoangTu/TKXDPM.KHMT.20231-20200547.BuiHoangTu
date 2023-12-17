package hust.mssv20200547.pttkhtaims.views;

import hust.mssv20200547.pttkhtaims.controllers.MediaInHomeController;

import java.io.IOException;
import java.net.URL;

public class MediaInHomeView extends BaseView{
    private static final URL PATH = MediaInHomeView.class.getResource("/fxml/media-in-home.fxml");

    /**
     * prepare a new scene
     *
     * @throws IOException          if getSceneURL return null
     * @throws NullPointerException if cant find fxml file
     */
    public MediaInHomeView() throws IOException {
        super(PATH);
    }

    @Override
    public MediaInHomeController getController() {
        return (MediaInHomeController) super.getController();
    }
}
