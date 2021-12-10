package com.example.demo.shapes;


import com.example.demo.services.LineServices;
import org.springframework.beans.factory.annotation.Autowired;

public class Square extends Shape implements ShapeI {
    private Point p1, p2, first, second, third, forth;
    private double length, maxX, maxY, minX, minY;
    private int type;

    public Square() {
    }

    public Square(Point p1, Point p2, String color) {
        this.p1 = p1;
        this.p2 = p2;
        this.name = "square";
        this.color = color;
        constructSquare();
    }


    LineServices lineServices = new LineServices();

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

    public void constructSquare() {
        this.length = Math.abs(this.p1.getX() - this.p2.getX());
        this.maxX = Math.max(this.p1.getX(), this.p2.getX());
        this.maxY = Math.max(this.p1.getY(), this.p2.getY());
        this.minX = Math.min(this.p1.getX(), this.p2.getX());
        this.minY = Math.min(this.p1.getY(), this.p2.getY());
        if (this.p1.getX() == this.minX && this.p1.getY() == this.minY || this.p1.getX() == this.maxX && this.p1.getY() == this.minY ) {
            first = new Point(this.minX, this.minY);
            second = new Point(this.maxX, this.minY);
            third = new Point(this.minX, this.minY + length);
            forth = new Point(this.maxX, this.minY + length);

        } else if (this.p1.getX() == this.minX && this.p1.getY() == this.maxY   || this.p1.getX() == this.maxX && this.p1.getY() == this.maxY  ) {
            first = new Point(this.minX, this.maxY - length);
            second = new Point(this.maxX, this.maxY - length);
            third = new Point(this.minX, this.maxY);
            forth = new Point(this.maxX, this.maxY);
        }
        if (this.p1.getX() == this.minX && this.p1.getY() == this.minY) {
            type = 1;
        } else if (this.p1.getX() == this.minX && this.p1.getY() == this.maxY) {
            type = 2;
        } else if (this.p1.getX() == this.maxX && this.p1.getY() == this.minY) {
            type = 3;
        } else if (this.p1.getX() == this.maxX && this.p1.getY() == this.maxY) {
            type = 4;
        }
    }

    @Override
    public boolean range(Point click) {
        return lineServices.checkPoint(first, second, click) || lineServices.checkPoint(first, third, click) || lineServices.checkPoint(third, forth, click) || lineServices.checkPoint(second, forth, click);
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
        constructSquare();
    }

}
