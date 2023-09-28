package com.example.demo2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HelloController extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Polygon");
        Pane root = new Pane();
        Canvas canvas = new Canvas(400, 400);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        int[] x = {100, 200, 300};
        int[] y = {300, 100, 300};
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE};

        Point p1 = new Point(x[0],y[0],colors[0]);
        Point p2 = new Point(x[1],y[1],colors[1]);
        Point p3 = new Point(x[2],y[2],colors[2]);

        rasterizeTriangle(gc, p1,p2,p3,colors);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void rasterizeTriangle(GraphicsContext gc, Point p1, Point p2, Point p3, Color[] colors) {
        int minX = Math.min(p1.x, Math.min(p2.x, p3.x));
        int minY = Math.min(p1.y, Math.min(p2.y, p3.y));
        int maxX = Math.max(p1.x, Math.max(p2.x, p3.x));
        int maxY = Math.max(p1.y, Math.max(p2.y, p3.y));

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                Double[] bar = MyMath.calculateBar(p1,p2,p3,x,y);

                if (MyMath.isInside(bar[0], bar[1], bar[2])){
                    gc.setFill(MyMath.intropolate(bar, colors));
                    gc.fillRect(x, y, 1, 1);
                }
            }
        }
    }

}

