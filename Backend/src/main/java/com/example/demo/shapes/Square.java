package com.example.demo.shapes;


import com.example.demo.services.LineServices;
import org.springframework.beans.factory.annotation.Autowired;

public class Square extends Shape implements ShapeI {
    private Point p1, p2, first, second, third, forth;
    private double length, maxX, maxY, minX, minY;
    private int type;

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
        if (p1.getX() == minX && p1.getY() == minY) {
            type = 1;
        } else if (p1.getX() == minX && p1.getY() == maxY) {
            type = 2;
        } else if (p1.getX() == maxX && p1.getY() == minY) {
            type = 3;
        } else if (p1.getX() == maxX && p1.getY() == maxY) {
            type = 4;
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

    @Override
    public void afterMove(Point click) {
        setP1(click);
        changeP2(click);
    }

    private void changeP2(Point click) {
        switch (this.type) {
            case 1:
                setP2(new Point(click.getX() + length, click.getY() + length));
                break;
            case 2:
                setP2(new Point(click.getX() + length, click.getY() - length));
                break;
            case 3:
                setP2(new Point(click.getX() - length, click.getY() + length));
                break;
            case 4:
                setP2(new Point(click.getX() - length, click.getY() - length));
                break;
            default:
        }
    }

    @Override
    public boolean range(Point click) {
        return lineServices.checkPoint(first, second, click) || lineServices.checkPoint(first, third, click) || lineServices.checkPoint(third, forth, click) || lineServices.checkPoint(second, forth, click);
    }

    @Override
    public Point[] getPoints() {
        Point[] arr = new Point[3];
        arr[0] = p1;
        arr[1] = p2;
        arr[2] = null;
        return arr;
    }

}
