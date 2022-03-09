import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author : Sanu Vithanage
 * @since : 0.1.0
 **/
public class AppInitializer extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = FXMLLoader.load(this.getClass().getResource("/view/CustomerForm.fxml"));
        Scene scene=new Scene(root);
        primaryStage.centerOnScreen();
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
