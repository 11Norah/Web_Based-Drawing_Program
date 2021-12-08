package com.example.demo.shapes;

import com.example.demo.response.ResponseObjectI;

public class Circle extends Shape implements ResponseObjectI {
    int radius;
    Point center;

    public Point getCenter() {
        return center;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public void draw() {

    }
}
