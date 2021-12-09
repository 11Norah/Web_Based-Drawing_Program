package com.example.demo.shapes;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeName;

public class Circle extends Shape {
    double radius;
    Point center, second;

    public Circle() {}

    public Circle(Point center, Point second, String color) {
        this.center = center;
        this.second = second;
        this.radius = Math.sqrt((Math.pow((center.getX() - second.getX()), 2) + Math.pow((center.getY() - second.getY()), 2)));
        this.name = "circle";
        this.color = color;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }


    public boolean range(Point click) {
        double dist = Math.pow((center.getX() - click.getX()), 2) + Math.pow((center.getY() - click.getY()), 2);
        if (dist <= radius * radius) {
            return true;
        }
        return false;
    }

    @Override
    public Point[] getPoints() {
        Point[] arr = new Point[3];
        arr[0] = center;
        arr[1] = second;
        arr[2] = null;
        return arr;
    }

    public void afterMove(Point newCenter) {
        this.center = newCenter;
        this.second.setX(newCenter.getX());
        this.second.setY(newCenter.getY() + radius);
    }

}
