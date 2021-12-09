package com.example.demo.shapes;

import com.example.demo.services.LineServices;
import org.springframework.beans.factory.annotation.Autowired;


public class Rectangle extends Shape implements ShapeI{
    private double width, height, maxX, maxY, minX, minY;
    private Point p1, p2;


    @Autowired
    LineServices lineServices;

    public Rectangle(Point p1, Point p2, String color) {
        this.p1 = p1;
        this.p2 = p2;
        this.width = Math.abs(p1.getY() - p2.getY());
        this.height = Math.abs(p1.getX() - p2.getX());
        this.name = "rectangle";
        this.color = color;
        maxX = Math.max(p1.getX(), p2.getX());
        maxY = Math.max(p1.getY(), p2.getY());
        minX = Math.min(p1.getX(), p2.getX());
        minY = Math.min(p1.getY(), p2.getY());
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public boolean range(Point click) {
        return lineServices.checkPoint(new Point(maxX, maxY), new Point(maxX, minY), click) || lineServices.checkPoint(new Point(maxX, maxY), new Point(minX, maxY), click) || lineServices.checkPoint(new Point(minX, minY), new Point(minX, maxY), click) || lineServices.checkPoint(new Point(minX, minY), new Point(maxX, minY), click);
    }
}
