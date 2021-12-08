package com.example.demo.shapes;

import com.example.demo.response.ResponseObjectI;

import java.awt.*;

public class Circle extends Shape {
    double radius;
    Point center,second;



    public Circle(Point center, Point second) {
        this.center = center;
        this.second = second;
        this.radius = Math.sqrt((Math.pow((center.x-second.x),2)+Math.pow((center.y-second.y),2)));
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
    public double getR() {
        return radius;
    }
    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public void draw() {

    }
}
