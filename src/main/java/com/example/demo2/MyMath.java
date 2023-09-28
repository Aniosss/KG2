package com.example.demo2;

import javafx.scene.paint.Color;

public class MyMath {
    public static Double[] calculateBar(Point p1, Point p2, Point p3, int x, int y){
        double alpha = (double) ((p2.y - p3.y) * (x - p3.x) + (p3.x - p2.x) * (y - p3.y)) / ((p2.y - p3.y) * (p1.x - p3.x) + (p3.x - p2.x) * (p1.y - p3.y));
        double beta = (double) ((p3.y - p1.y) * (x - p3.x) + (p1.x - p3.x) * (y - p3.y)) / ((p2.y - p3.y) * (p1.x - p3.x) + (p3.x - p2.x) * (p1.y - p3.y));
        double gamma = 1 - alpha - beta;
        return new Double[]{alpha, beta, gamma};
    }
    public static boolean isInside(double alpha, double beta, double gamma){
        return alpha >= 0 && beta >= 0 && gamma >= 0;
    }

    public static Color intropolate(Double[] bar, Color[] colors){
        double r = bar[0] * colors[0].getRed() + bar[1] * colors[1].getRed() + bar[2] * colors[2].getRed();
        double g = bar[0] * colors[0].getGreen() + bar[1] * colors[1].getGreen() + bar[2] * colors[2].getGreen();
        double b = bar[0] * colors[0].getBlue() + bar[1] * colors[1].getBlue() + bar[2] * colors[2].getBlue();
        return Color.color(r,g,b);
    }
}
