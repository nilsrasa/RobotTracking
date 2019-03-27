package sample;

import sample.Space.Vector2D;

import java.util.Random;

public class TestData {
    public static final Vector2D[] corners = {
            new Vector2D(40, 366), //Top left
            new Vector2D(1044, 357), //Top right
            new Vector2D(1051, 987), //Bottom right
            new Vector2D(45, 993) // Bottom left
    },
            goals = {
            new Vector2D(43, 543), //Left goal top
            new Vector2D(41, 657), //Left goal bottom
            new Vector2D(1047, 501), //Right goal top
            new Vector2D(1050, 756) //Right goal bottom
    };

    public static final Vector2D cross = new Vector2D(542, 698),
            robot = new Vector2D(97, 403);

    public static Vector2D[] getBalls(){
        Vector2D[] balls = new Vector2D[10];
        Random random = new Random();
        for (int i = 0; i < 10; i++){
            balls[i] = new Vector2D(
                    random.nextInt(1051-45) + 45,
                    random.nextInt(993-366) + 366
            );
        }

        return balls;
    }

    public static Vector2D[][] getObstaclePoints(){
        Vector2D[][] points = new Vector2D[6][2];

        //Top bar
        points[0][0] = corners[0];
        points[0][1] = corners[1];
        //Bottom bar
        points[1][0] = corners[2];
        points[1][1] = corners[3];
        //Lef top goal
        points[2][0] = corners[0];
        points[2][1] = goals[0];
        //left goal bottm
        points[3][0] = corners[3];
        points[3][1] = goals[1];
        //right goal top
        points[4][0] = corners[1];
        points[4][1] = goals[2];
        //right goal bottom
        points[5][0] = corners[2];
        points[5][1] = goals[3];

        return points;
    }


}
