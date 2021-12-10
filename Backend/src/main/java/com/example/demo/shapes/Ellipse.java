package com.example.demo.shapes;

public class Ellipse extends Shape implements ShapeI {
    private double a, b;
    private Point center, p1, p2;
    private boolean isHorizontal;

    public Ellipse() {
    }

    public Ellipse(Point center, Point p1, Point p2, String color) {
        this.center = center;
        this.p1 = p1;
        this.p2 = p2;
        this.name = "ellipse";
        this.color = color;
        constructEllipse();
    }


    @Override
    public void afterMove(Point newCenter) {
        this.center = newCenter;
        p1.setX(newCenter.getX());
        p1.setY(newCenter.getY() + a);
        p2.setY(newCenter.getY());
        p2.setX(newCenter.getX() + b);

    }

    public boolean range(Point click) {
        double dist = (Math.pow((center.getX() - click.getX()), 2) / (a * a)) + (Math.pow((center.getY() - click.getY()), 2) / (b * b));
        if (Math.abs(dist - 1) <= .1) {
            return true;
        }
        return false;
    }

    @Override
    public Point[] getPoints() {
        Point[] arr = new Point[3];
        arr[0] = center;
        arr[1] = p1;
        arr[2] = p2;
        return arr;
    }

    public void constructEllipse() {
        this.a = Math.sqrt(Math.pow((center.getX() - p1.getX()), 2) + Math.pow((center.getY() - p1.getY()), 2));
        this.b = Math.sqrt(Math.pow((center.getX() - p2.getX()), 2) + Math.pow((center.getY() - p2.getY()), 2));
        isHorizontal = a >= b;
    }

    @Override
    public void resize(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
        constructEllipse();
    }
}
