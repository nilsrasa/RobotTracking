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
import sample.Space.Grid;
import sample.Space.Vector2D;

import java.util.Random;

public class Main extends Application {
    Grid grid;
    float width = 600, height = 400;

    @Override
    public void start(Stage stage) throws Exception{
        Group root = new Group();
        Canvas canvas = new Canvas(width, height);
        GraphicsContext context = canvas.getGraphicsContext2D();
        createObjects(context);

        root.getChildren().add(canvas);
        stage.setTitle("JavaFX Scene Graph Demo");
        stage.setScene(new Scene(root, Color.BLACK));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void createObjects(GraphicsContext context){
        //Grid
        grid = new Grid(width, height);
        Vector2D[] vA = TestData.corners;
        grid.setScale(vA[0], vA[1], vA[2], vA[3]);
        grid.setColor(Color.GRAY);
        grid.draw(context);

        //The Robot:
        Robot robot = new Robot();
        robot.setPos(grid.translatePos(TestData.robot));
        robot.setWidth(30);
        robot.setHeight(30);
        robot.setColor(Color.GREEN);
        robot.draw(context);
        //robot.setSpeed(.3f);
        //robot.moveTo(new Vector2D(500, 250));

        //The obstacles:
        /*Forhindring[] obstacles = new Forhindring[8];
        Vector2D[][] points = TestData.getObstaclePoints();

        for (int i = 0; i < obstacles.length; i++){
            obstacles[i] = new Forhindring();
            obstacles[i].setWidth(15);
            obstacles[i].setColor(Color.RED);
            if (i < 6)
                obstacles[i].setPoints(
                        grid.translatePos(points[i][0]),
                        grid.translatePos(points[i][1])
                );
            else if (i == 6)
                obstacles[i].setPoints(
                        new Vector2D(width/2 - 15, height/2),
                        new Vector2D(width/2 + 15, height/2)
                );
            else
                obstacles[i].setPoints(
                        new Vector2D(width/2, height/2-15),
                        new Vector2D(width/2, height/2+15)
                );
            obstacles[i].draw(context);

        }*/

        //Balls:
        vA = TestData.getBalls();
        Bold[] balls = new Bold[vA.length];
        for (int i = 0; i < vA.length; i++){
            balls[i] = new Bold();
            balls[i].setWidth(8);
            balls[i].setHeight(8);
            balls[i].setColor(Color.WHITE);
            balls[i].setPos(
                    grid.translatePos(vA[i])
            );
            balls[i].draw(context);
        }
    }
}
