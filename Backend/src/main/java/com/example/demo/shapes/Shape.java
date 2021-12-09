package com.example.demo.shapes;

import java.awt.Point;
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
    public void afterMove(com.example.demo.shapes.Point click) {

    }

    @Override
    public boolean range(com.example.demo.shapes.Point click) {
        return false;
    }

    @Override
    public com.example.demo.shapes.Point[] getPoints() {
        return new com.example.demo.shapes.Point[0];
    }


}
