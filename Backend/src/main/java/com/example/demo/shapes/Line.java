package com.example.demo.shapes;

import com.example.demo.response.ResponseObjectI;

public class Line extends Shape implements ResponseObjectI {
    private Point p1, p2;

    Line(Point p1, Point p2) {
        this.p1 = new Point(p1.getX(), p1.getY());
        this.p2 = new Point(p2.getX(), p2.getY());
    }

    public Point getPoint1() {
        return p1;
    }

    public Point getPoint2() {
        return p2;
    }
}
