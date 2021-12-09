package com.example.demo.shapes;

import com.example.demo.response.ResponseObjectI;


public class Line extends Shape implements ResponseObjectI {
    private Point p1, p2;
    private double length, m, c;

    public Line(Point p1, Point p2, String Color) {
        this.p1 = p1;
        this.p2 = p2;
        this.length = Math.sqrt((Math.pow((p1.getX() - p2.getX()), 2) + Math.pow((p1.getY() - p2.getY()), 2)));
        this.name = "line";
        this.color = color;
        c = (p1.getX() * p2.getY() - p1.getY() * p2.getX()) / (p1.getX() - p2.getX());
        m = (p1.getY() - c) / p1.getX();

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

    public boolean range(Point click) {
        double y = m * click.getX() + c;
        double dist1 = Math.pow((p1.getX() - click.getX()), 2) + Math.pow((p1.getY() - click.getY()), 2);
        double dist2 = Math.pow((p2.getX() - click.getX()), 2) + Math.pow((p2.getY() - click.getY()), 2);
        if (dist1 <= length && dist2 <= length && click.getY() == y) {
            return true;
        }
        return false;
    }
}
