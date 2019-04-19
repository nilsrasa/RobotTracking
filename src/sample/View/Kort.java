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

import java.util.Arrays;
import java.util.HashSet;
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

    public float getWIDTH() {
        return WIDTH;
    }

    public float getHEIGHT() {
        return HEIGHT;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public void setNodes(Node[][] nodes) {
        this.nodes = nodes;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public void setBalls(Set<Bold> balls) {
        this.balls = balls;
    }

    public void setGoals(Set<Mål> goals) {
        this.goals = goals;
    }

    public void setObstacles(Set<Forhindring> obstacles) {
        this.obstacles = obstacles;
    }

    public void update(){
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.clearRect(0,0,WIDTH,HEIGHT); //Clear canvas

        grid.draw(context);

    }
}
