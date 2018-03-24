package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("snake.fxml"));
        fxmlLoader.load();
        Parent root = fxmlLoader.getRoot();
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("snake.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("snake.fxml"));
        Controller controller = fxmlLoader.getController();
        primaryStage.setTitle("Snake");
        Scene scene = new Scene(root, 600, 800);
        controller.initialize(scene);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
