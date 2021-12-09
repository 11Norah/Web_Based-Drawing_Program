package com.example.demo.shapes;


import com.example.demo.services.LineServices;
import org.springframework.beans.factory.annotation.Autowired;

public class Square extends Shape implements ShapeI {
    private Point p1, p2, first, second, third, forth;
    private double length, maxX, maxY, minX, minY;

    public Square(Point p1, Point p2, String color) {
        this.p1 = p1;
        this.p2 = p2;
        this.length = Math.abs(p1.getX() - p2.getX());
        this.name = "square";
        this.color = color;
        maxX = Math.max(p1.getX(), p2.getX());
        maxY = Math.max(p1.getY(), p2.getY());
        minX = Math.min(p1.getX(), p2.getX());
        minY = Math.min(p1.getY(), p2.getY());
        if (p1.equals(new Point(minX, minY)) || p1.equals(new Point(maxX, minY))) {
            first = new Point(minX, minY);
            second = new Point(maxX, minY);
            third = new Point(minX, minY + length);
            forth = new Point(maxX, minY + length);

        } else if (p1.equals(new Point(minX, maxY)) || p1.equals(new Point(maxX, maxY))) {
            first = new Point(minX, maxY - length);
            second = new Point(maxX, maxY - length);
            third = new Point(minX, maxY);
            forth = new Point(maxX, maxY);

        }
    }

    @Autowired
    LineServices lineServices;

    public double getLength() {
        return length;
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public boolean range(Point click) {
        return lineServices.checkPoint(first, second, click) || lineServices.checkPoint(first, third, click) || lineServices.checkPoint(third, forth, click) || lineServices.checkPoint(second, forth, click);
    }


}
