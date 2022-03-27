package test;

public class Test extends DBclass.DataBase_connection implements javafx.fxml.Initializable {
    @javafx.fxml.FXML
    private javafx.scene.control.Label lbl_display;

    @javafx.fxml.FXML
    private javafx.scene.control.MenuButton MenuButton;

    @javafx.fxml.FXML
    private javafx.scene.control.Button button;

    @javafx.fxml.FXML
    void on_button(javafx.event.ActionEvent event) {

    }
    @javafx.fxml.FXML
    void omm_pane(javafx.scene.input.MouseEvent event) {
        button.setEffect(null);
    }

    @javafx.fxml.FXML
    void omm_button(javafx.scene.input.MouseEvent event) {
        ShadowButtons(button);


    }
    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        try {
            java.io.FileInputStream input = new java.io.FileInputStream("src/controllers.image/users_settings.jpg");
            javafx.scene.image.Image image = new javafx.scene.image.Image(input);
            javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView(image);

            MenuButton.setGraphic(imageView);
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    private void ShadowButtons(javafx.scene.control.Button but){
        javafx.scene.effect.DropShadow dropShadow = new javafx.scene.effect.DropShadow();
        dropShadow.setOffsetX(5.0);
        dropShadow.setOffsetY(5.0);
        dropShadow.setColor(javafx.scene.paint.Color.BLUE);
        dropShadow.setRadius(15);
        but.setEffect(dropShadow);
    }
}
