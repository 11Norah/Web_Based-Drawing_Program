package com.example.demo.shapes;

import com.example.demo.services.LineServices;
import org.springframework.beans.factory.annotation.Autowired;


public class Rectangle extends Shape implements ShapeI {
    private double width, height, maxX, maxY, minX, minY;
    private Point p1, p2;
    private int type;


    @Autowired
    LineServices lineServices;

    public Rectangle() {}

    public Rectangle(Point p1, Point p2, String color) {
        this.p1 = p1;
        this.p2 = p2;
        this.name = "rectangle";
        this.color = color;
        constructRectangle();
    }

    public void constructRectangle() {
        this.width = Math.abs(this.p1.getY() - this.p2.getY());
        this.height = Math.abs(this.p1.getX() - this.p2.getX());
        this.maxX = Math.max(this.p1.getX(), this.p2.getX());
        this.maxY = Math.max(this.p1.getY(), this.p2.getY());
        this.minX = Math.min(this.p1.getX(), this.p2.getX());
        this.minY = Math.min(this.p1.getY(), this.p2.getY());
        if (this.p1.getX() == minX && this.p1.getY() == minY) {
            type = 1;
        } else if (this.p1.getX() == minX && this.p1.getY() == maxY) {
            type = 2;
        } else if (this.p1.getX() == maxX && this.p1.getY() == minY) {
            type = 3;
        } else if (this.p1.getX() == maxX && this.p1.getY() == maxY) {
            type = 4;
        }
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


    @Override
    public void afterMove(Point click) {
        setP1(click);
        changeP2(click);
    }

    private void changeP2(Point click) {
        switch (this.type) {
            case 1:
                setP2(new Point(click.getX() + height, click.getY() + width));
                break;
            case 2:
                setP2(new Point(click.getX() + height, click.getY() - width));
                break;
            case 3:
                setP2(new Point(click.getX() - height, click.getY() + width));
                break;
            case 4:
                setP2(new Point(click.getX() - height, click.getY() - width));
                break;
            default:
        }
    }

    public boolean range(Point click) {
        return lineServices.checkPoint(new Point(maxX, maxY), new Point(maxX, minY), click) || lineServices.checkPoint(new Point(maxX, maxY), new Point(minX, maxY), click) || lineServices.checkPoint(new Point(minX, minY), new Point(minX, maxY), click) || lineServices.checkPoint(new Point(minX, minY), new Point(maxX, minY), click);
    }

    @Override
    public Point[] getPoints() {
        Point[] arr = new Point[3];
        arr[0] = this.p1;
        arr[1] = this.p2;
        arr[2] = null;
        return arr;
    }

    @Override
    public void resize(Point p1, Point p2) {
        this.p2 = p1;
        constructRectangle();
    }
}
