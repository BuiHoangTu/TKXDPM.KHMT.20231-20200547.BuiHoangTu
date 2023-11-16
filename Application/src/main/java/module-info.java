module hust.mssv.pttkhtaims {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens hust.mssv20200547.pttkhtaims to javafx.fxml;
    exports hust.mssv20200547.pttkhtaims;
}