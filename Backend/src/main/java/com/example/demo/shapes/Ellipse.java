package com.example.demo.shapes;

import com.example.demo.response.ResponseObjectI;

import java.awt.*;

public class Ellipse extends Shape  {
    private int r1,r2;
    private Point center,p1,p2;

    public Ellipse(Point center, Point p1, Point p2,String color) {
        this.center = center;
        this.p1 = p1;
        this.p2 = p2;
        this.name = "ellipse";
        this.color = color;
    }

    public int getR1() {
        return r1;
    }

    public int getR2() {
        return r2;
    }

    public Point getCenter() {
        return center;
    }
}
