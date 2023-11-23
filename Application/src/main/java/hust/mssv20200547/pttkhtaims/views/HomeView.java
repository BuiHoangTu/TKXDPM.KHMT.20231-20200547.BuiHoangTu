package hust.mssv20200547.pttkhtaims.views;

import java.io.IOException;
import java.net.URL;

public class HomeView extends BaseView {
    private static final String URI = "/hust/mssv20200547/pttkhtaims/home-screen-view.fxml";

    /**
     * Constructor
     *
     * @throws IOException          if getSceneURL return null
     * @throws NullPointerException if mainStage is null
     */
    public HomeView() throws IOException {
        super();
    }

    @Override
    protected URL getSceneURL() {
        return HomeView.class.getResource(URI);
    }
}
