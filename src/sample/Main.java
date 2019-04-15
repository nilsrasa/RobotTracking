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
import sample.Objects.Mål;
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
        stage.setScene(new Scene(root, Color.DARKGREEN));
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
        grid.setColor(Color.GREEN);

        //The Robot:
        Robot robot = new Robot();
        robot.setPos(grid.translatePos(TestData.robot));
        robot.setWidth(30);
        robot.setHeight(30);
        robot.setColor(Color.LIGHTGREEN);
        grid.addObject(robot);
        //robot.setSpeed(.3f);
        //robot.moveTo(new Vector2D(500, 250));

        //Obstacles
        Forhindring obstacle = new Forhindring();
        obstacle.setPos(grid.getCenterPos());
        obstacle.setWidth(grid.CELL_SPACING.getX());
        obstacle.setHeight(grid.CELL_SPACING.getY()*5);
        obstacle.setColor(Color.RED);
        grid.addObject(obstacle);

        obstacle = new Forhindring();
        obstacle.setPos(grid.getCenterPos());
        obstacle.setWidth(grid.CELL_SPACING.getX()*5);
        obstacle.setHeight(grid.CELL_SPACING.getY());
        obstacle.setColor(Color.RED);
        grid.addObject(obstacle);

        //Goals
        Mål goal = new Mål();
        goal.setPos(grid.getLeftCenterPos());
        goal.setWidth(5);
        goal.setHeight(grid.GOAL_LEFT*grid.CELL_SPACING.getY());
        goal.setColor(Color.LIGHTGRAY);
        grid.addObject(goal);

        goal = new Mål();
        goal.setPos(grid.getRightCenterPos());
        goal.setWidth(5);
        goal.setHeight(grid.GOAL_RIGHT*grid.CELL_SPACING.getY());
        goal.setColor(Color.LIGHTGRAY);
        grid.addObject(goal);

        //Balls:
        vA = TestData.getBalls();
        Bold[] balls = new Bold[vA.length];
        for (int i = 0; i < vA.length; i++){
            balls[i] = new Bold();
            balls[i].setWidth(grid.CELL_SPACING.getX());
            balls[i].setHeight(grid.CELL_SPACING.getY());
            balls[i].setColor(Color.WHITE);
            balls[i].setPos(
                    grid.translatePos(vA[i])
            );
            grid.addObject(balls[i]);
        }

        //Draw the map
        grid.draw(context);
    }
}
