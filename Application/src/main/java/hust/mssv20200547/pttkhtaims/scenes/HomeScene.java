package hust.mssv20200547.pttkhtaims.scenes;

import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HomeScene extends BaseScene {
    private static final String URI = "/hust/mssv20200547/pttkhtaims/home-screen-view.fxml";

    /**
     * Constructor
     *
     * @throws IOException          if getSceneURL return null
     * @throws NullPointerException if mainStage is null
     */
    public HomeScene() throws IOException {
        super();
    }

    @Override
    protected URL getSceneURL() {
        return HomeScene.class.getResource(URI);
    }
}
