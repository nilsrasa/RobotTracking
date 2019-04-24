package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import sample.View.Colors;
import sample.View.Kort;

public class Main extends Application {
    float width = 600, height = 400;

    @Override
    public void start(Stage stage) throws Exception{
        Group root = new Group();
        Canvas canvas = new Canvas(width, height);

        Controller controller = new Controller();
        controller.createMap(new Kort(canvas));


        root.getChildren().add(canvas);
        stage.setTitle("JavaFX Scene Graph Demo");
        stage.setScene(new Scene(root, Colors.BACKGROUND));
        stage.show();
        controller.start();
    }




    public static void main(String[] args) {
        launch(args);
    }

}
