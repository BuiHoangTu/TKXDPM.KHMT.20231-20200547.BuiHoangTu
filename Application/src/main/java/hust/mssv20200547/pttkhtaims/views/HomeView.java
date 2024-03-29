package hust.mssv20200547.pttkhtaims.views;

import hust.mssv20200547.pttkhtaims.controllers.HomeController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class HomeView extends BaseView {
    private static final URL PATH = HomeView.class.getResource("/fxml/home-screen-view.fxml");

    /**
     * Constructor
     *
     * @throws IOException          if getSceneURL return null
     * @throws NullPointerException if mainStage is null
     */
    public HomeView() throws IOException, SQLException {
        super(PATH);

//        HomeController controller = this.loader.getController();
//        controller.setDefaultValues(storeService.searchMediaTitleInStore("", 20));
    }

    @Override
    public HomeController getController() {
        return (HomeController) super.getController();
    }
}
