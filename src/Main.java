import controller.main.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        MainController.showMainWindow(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
