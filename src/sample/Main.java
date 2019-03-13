package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Objects.Bold;
import sample.Objects.Forhindring;
import sample.Objects.Robot;
import sample.Space.Vector2D;

import java.util.Random;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Group root = new Group();
        Canvas canvas = new Canvas(600, 600);
        GraphicsContext context = canvas.getGraphicsContext2D();
        createObjects(context);
        //TODO: move objects

        root.getChildren().add(canvas);
        stage.setTitle("JavaFX Scene Graph Demo");
        stage.setScene(new Scene(root, Color.BLACK));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void createObjects(GraphicsContext context){
        //The Robot:
        Robot robot = new Robot();
        robot.setPos(new Vector2D(150,200));//Position is in the top left corner
        robot.setWidth(30);
        robot.setHeight(30);
        robot.setColor(Color.GREEN);
        robot.draw(context);

        //The obstacles:
        Vector2D[] pos = {
                new Vector2D(300, 450), //TOP
                new Vector2D(300, 150), //BOTTOM
                new Vector2D(30, 300), //LEFT
                new Vector2D(570, 300) //RIGHT
        };

        float[] heights = {
                15, 15,
                315, 315
        };

        float[] widths = {
                555, 555,
                15, 15
        };

        Forhindring[] obstacles = new Forhindring[4];

        for(int i = 0; i < 4; i++){
            obstacles[i] = new Forhindring();
            obstacles[i].setColor(Color.RED);
            obstacles[i].setWidth(widths[i]);
            obstacles[i].setHeight(heights[i]);
            obstacles[i].setPos(pos[i]);
            obstacles[i].draw(context);
        }

        //Balls:
        Bold[] balls = new Bold[10];
        Random random = new Random();
        for (int i = 0; i < 10; i++){
            balls[i] = new Bold();
            balls[i].setWidth(8);
            balls[i].setHeight(8);
            balls[i].setColor(Color.WHITE);
            balls[i].setPos(
                    new Vector2D(
                            random.nextInt(570-30) + 30,
                            random.nextInt(450-150) + 150
                    )
            );
            balls[i].draw(context);
        }


    }
}
