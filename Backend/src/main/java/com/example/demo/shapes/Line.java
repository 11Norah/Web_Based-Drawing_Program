package com.example.demo.shapes;


import com.example.demo.services.LineServices;
import org.springframework.beans.factory.annotation.Autowired;

public class Line extends Shape implements ShapeI {
    private Point p1, p2;
    private double length, m, c;
    private int type;
    private boolean isVertical = false;


    LineServices lineServices = new LineServices();

    public Line(Point p1, Point p2, String color) {
        this.p1 = p1;
        this.p2 = p2;
        this.name = "line";
        this.color = color;
        constructLine();
    }


    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    @Override
    public void afterMove(Point click) {
        this.p2 = lineServices.afterMove(click, this.m, this.p1, this.p2, this.length);
    }


    public boolean range(Point click) {
        return lineServices.checkPoint(this.p1, this.p2, click);
    }

    @Override
    public Point[] getPoints() {
        Point[] arr = new Point[3];
        arr[0] = p1;
        arr[1] = p2;
        arr[2] = null;
        return arr;
    }

    private void constructLine() {
        this.length = lineServices.getLength(this.p1, this.p2);
        if (Math.abs(this.p1.getX() - this.p2.getX()) <= 5) {
            isVertical = true;
        } else {
            this.c = lineServices.getC(this.p1, this.p2);
            this.m = lineServices.getM(this.p1, this.p2, this.c);
            this.type = lineServices.detectType(this.p1, this.p2);
        }
    }

    @Override
    public void resize(Point p1, Point p2) {
        this.p2 = p1;
        constructLine();
    }
}
