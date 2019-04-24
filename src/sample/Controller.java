package sample;

import javafx.animation.AnimationTimer;
import sample.Objects.Bold;
import sample.Objects.Forhindring;
import sample.Objects.Mål;
import sample.Objects.Robot;
import sample.Space.Grid;
import sample.Space.Node;
import sample.Space.Vector2D;
import sample.View.Colors;
import sample.View.Kort;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Controller {
    private final int NANOMILLI = 1000000,
    UPDATETIME = 100000*NANOMILLI;
    Kort map;
    private long lastTime;


    public void createMap(Kort map){
        this.map = map;
    }

    public void start(){
        createObjects();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                //TODO: fetch data
                //TODO: update position
                //map.getRobot().getPos().add(Vector2D.RIGHT).add(Vector2D.DOWN);
                //map.getRobot().setRotation(map.getRobot().getRotation()+15);

                //Runs every UPDATETIME
                if (now - UPDATETIME > lastTime) {
                    lastTime = now;

                    Grid grid = map.getGrid();

                    Vector2D[] vA = TestData.getBalls();
                    map.setBalls(createBalls(vA, grid));

                    Bold[] balls = map.getBalls().toArray(new Bold[0]);
                    map.getRobot().setTarget(balls[new Random().nextInt(balls.length)]);

                    map.update();
                }
            }
        }.start();
    }

    private void createObjects(){
        //Grid
        Grid grid = new Grid(map.getWIDTH(), map.getHEIGHT());
        Vector2D[] vA = TestData.corners;
        grid.setScale(vA[0], vA[1], vA[2], vA[3]);
        grid.setColor(Colors.GRID);
        map.setGrid(grid);

        //Nodes
        Node[][] nodes = new Node[(int)grid.CELLS_HOR][(int)grid.CELLS_VER];
        for (int i = 0; i < nodes.length; i++){
            for (int j = 0; j < nodes[i].length; j++){
                float x, y;
                x = grid.CELL_SPACING.getX()*i + grid.CELL_SPACING.getX()/2;
                y = grid.CELL_SPACING.getY()*j + grid.CELL_SPACING.getY()/2;
                nodes[i][j] = new Node(x, y);
                if (Debug.DEBUG){
                    nodes[i][j].setColor(Colors.NODE);
                    map.addDebugObject(nodes[i][j]);
                }
            }
        }
        map.setNodes(nodes);

        //The Robot:
        Robot robot = new Robot();
        robot.setPos(grid.translatePos(TestData.robot));
        robot.setWidth(grid.CELL_SPACING.getX()*1.5f);
        robot.setHeight(grid.CELL_SPACING.getY()*1.5f);
        robot.setColor(Colors.ROBOT);
        map.setRobot(robot);

        //Obstacles
        Set<Forhindring> obstacles = new HashSet<>();
        Forhindring obstacle = new Forhindring();
        obstacle.setPos(grid.getCenterPos());
        obstacle.setWidth(grid.CELL_SPACING.getX());
        obstacle.setHeight(grid.CELL_SPACING.getY()*5);
        obstacle.setColor(Colors.OBSTACLE);
        obstacles.add(obstacle);

        obstacle = new Forhindring();
        obstacle.setPos(grid.getCenterPos());
        obstacle.setWidth(grid.CELL_SPACING.getX()*5);
        obstacle.setHeight(grid.CELL_SPACING.getY());
        obstacle.setColor(Colors.OBSTACLE);
        obstacles.add(obstacle);
        map.setObstacles(obstacles);

        //Goals
        Set<Mål> goals = new HashSet<>();
        Mål goal = new Mål();
        goal.setPos(grid.getLeftCenterPos());
        goal.setWidth(5);
        goal.setHeight(grid.GOAL_LEFT*grid.CELL_SPACING.getY());
        goal.setColor(Colors.GOAL);
        goals.add(goal);

        goal = new Mål();
        goal.setPos(grid.getRightCenterPos());
        goal.setWidth(5);
        goal.setHeight(grid.GOAL_RIGHT*grid.CELL_SPACING.getY());
        goal.setColor(Colors.GOAL);
        goals.add(goal);
        map.setGoals(goals);

        //Balls:
        vA = TestData.getBalls();

        map.setBalls(createBalls(vA, grid));

        //Draw the map
        map.update();
    }

    private Set<Bold> createBalls(Vector2D[] vA, Grid grid){
        Bold[] balls = new Bold[vA.length];
        for (int i = 0; i < vA.length; i++){
            balls[i] = new Bold();
            balls[i].setWidth(grid.CELL_SPACING.getX());
            balls[i].setHeight(grid.CELL_SPACING.getY());
            balls[i].setColor(Colors.BALL);
            balls[i].setPos(
                    grid.translatePos(vA[i])
            );
        }
        return new HashSet<>(Arrays.asList(balls));
    }
}
