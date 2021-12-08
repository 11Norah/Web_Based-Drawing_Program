package com.example.demo.shapes;

import com.example.demo.response.ResponseObjectI;

import java.awt.Point;

public class Line extends Shape implements ResponseObjectI {
    private Point p1, p2;
    private double length;

    public Line(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.length = Math.sqrt((Math.pow((p1.x-p2.x),2)+Math.pow((p1.y-p2.y),2)));
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public Point getPoint1() {
        return p1;
    }

    public Point getPoint2() {
        return p2;
    }
}
