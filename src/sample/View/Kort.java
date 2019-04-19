package sample.View;

import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Objects.Bold;
import sample.Objects.Forhindring;
import sample.Objects.Mål;
import sample.Objects.Robot;
import sample.Space.Grid;
import sample.Space.Node;
import sample.Space.Vector2D;
import sample.TestData;
import sample.View.IDrawable;

import java.util.List;
import java.util.Set;

public class Kort {
    private Grid grid;
    private Node[][] nodes;
    private Robot robot;
    private Set<Bold> balls;
    private Set<Mål> goals;
    private Set<Forhindring> obstacles;
    private Canvas canvas;
    private boolean scaled = false;
    private final float WIDTH, HEIGHT;

    public Kort(Canvas canvas){
        this.canvas = canvas;
        this.WIDTH = (float)canvas.getWidth();
        this.HEIGHT = (float)canvas.getHeight();
    }

    private void createObjects(){
        GraphicsContext context = canvas.getGraphicsContext2D();

        //Grid
        grid = new Grid(WIDTH, HEIGHT);
        Vector2D[] vA = TestData.corners;
        grid.setScale(vA[0], vA[1], vA[2], vA[3]);
        grid.setColor(Color.GREEN);

        //Nodes
        nodes = new Node[(int)grid.CELLS_HOR][(int)grid.CELLS_VER];
        for (int i = 0; i < nodes.length; i++){
            for (int j = 0; j < nodes[i].length; j++){
                float x, y;
                x = grid.CELL_SPACING.getX()*i + grid.CELL_SPACING.getX()/2;
                y = grid.CELL_SPACING.getY()*j + grid.CELL_SPACING.getY()/2;
                nodes[i][j] = new Node(x, y);
                //Debugging
                //nodes[i][j].print(i,j);
                nodes[i][j].draw(context);
            }
        }

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
        update();
    }

    public Grid getGrid() {
        return grid;
    }

    public Node[][] getNodes() {
        return nodes;
    }

    public Robot getRobot() {
        return robot;
    }

    public Set<Bold> getBalls() {
        return balls;
    }

    public Set<Mål> getGoals() {
        return goals;
    }

    public Set<Forhindring> getObstacles() {
        return obstacles;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public boolean isScaled() {
        return scaled;
    }

    public void update(){
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.clearRect(0,0,WIDTH,HEIGHT); //Clear canvas

        grid.draw(context);

    }
}
