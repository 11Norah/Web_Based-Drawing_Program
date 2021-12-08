package com.example.demo.shapes;

import java.util.ArrayList;

public class Shape implements ShapeI {

    protected String color;
    protected ArrayList<Point> points;
    protected String name;

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void addPoint(Point point) {
        points.add(point);
    }

}
