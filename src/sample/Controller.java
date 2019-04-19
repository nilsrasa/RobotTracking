package sample;

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
import java.util.Set;

public class Controller {
    Kort map;

    public void createMap(Kort map){
        this.map = map;
    }

    public void start(){
        createObjects();
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
        robot.setWidth(30);
        robot.setHeight(30);
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
        map.setBalls(new HashSet<>(Arrays.asList(balls)));

        //Draw the map
        map.update();
    }
}
