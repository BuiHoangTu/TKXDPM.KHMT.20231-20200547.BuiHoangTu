package hust.mssv20200547.pttkhtaims.views;

import java.io.IOException;
import java.net.URL;

public class CartView extends BaseView{
    private static final URL PATH = CartView.class.getResource("/hust/mssv20200547/pttkhtaims/cart-screen-view.fxml");

    /**
     * prepare a new scene
     *
     * @throws IOException          if getSceneURL return null
     * @throws NullPointerException if cant find fxml file
     */
    public CartView() throws IOException {
    }

    @Override
    protected URL getSceneURL() throws NullPointerException {
        return PATH;
    }
}
