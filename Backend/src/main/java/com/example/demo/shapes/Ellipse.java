package com.example.demo.shapes;


public class Ellipse extends Shape implements ShapeI {
    private double maxR, minR, bSquare, aSquare;
    private Point center, p1, p2;
    private boolean isHorizontal, isVertical;


    public Ellipse(Point center, Point p1, Point p2, String color) {
        this.center = center;
        this.p1 = p1;
        this.p2 = p2;
        this.name = "ellipse";
        this.color = color;
        this.maxR = Math.sqrt(Math.pow((center.getX() - p1.getX()), 2) + Math.pow((center.getY() - p1.getY()), 2));
        this.minR = Math.sqrt(Math.pow((center.getX() - p2.getX()), 2) + Math.pow((center.getY() - p2.getY()), 2));
        bSquare = ((Math.pow((p2.getX() - center.getX()), 2) * Math.pow((p1.getY() - center.getY()), 2)) - (Math.pow((p1.getX() - center.getX()), 2) * Math.pow((p2.getY() - center.getY()), 2))) / (Math.pow((p2.getX() - center.getX()), 2) - Math.pow((p1.getX() - center.getX()), 2));
        aSquare = (bSquare * Math.pow((p1.getX() - center.getX()), 2)) / (bSquare - Math.pow((p1.getY() - center.getY()), 2));
        isHorizontal = aSquare >= bSquare;
        isVertical = !isHorizontal;

    }

    public double getMaxR() {
        return this.maxR;
    }

    public double getMinR() {
        return this.minR;
    }

    public Point getCenter() {
        return center;
    }

    @Override
    public void afterMove(Point newCenter) {
        this.center = newCenter;
        if (isHorizontal) {
            p1.setX(newCenter.getX());
            p1.setY(newCenter.getY() + maxR / 2);
            p2.setY(newCenter.getY());
            p2.setX(newCenter.getX() - minR / 2);
        } else {
            p1.setX(newCenter.getX());
            p1.setY(newCenter.getY() + minR / 2);
            p2.setY(newCenter.getY());
            p2.setX(newCenter.getX() - maxR / 2);
        }
    }

    public boolean range(Point click) {
        double dist = (Math.pow((center.getX() - click.getX()), 2) / aSquare) + (Math.pow((center.getY() - click.getY()), 2) / bSquare);
        if (dist <= 1) {
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
}
