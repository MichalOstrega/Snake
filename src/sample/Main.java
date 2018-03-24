package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //pobieram najpierw loadera, w celu przekazania obiektu sceny do Controllera
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("snake.fxml"));
        //Laduje loadera i pobieram korzen
        Parent root = fxmlLoader.load();
        //Pobieram obiekt controllera z Loadera
        Controller controller = fxmlLoader.getController();
        primaryStage.setTitle("Snake");
        //Tworze nowy obiekt Sceny
        Scene scene = new Scene(root, 600, 800);
        ///Wywolujac metode initialize przekazuje do Controllera obiekt sceny
        controller.initialize(scene);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
