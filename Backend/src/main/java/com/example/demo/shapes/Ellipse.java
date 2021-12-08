package com.example.demo.shapes;

import com.example.demo.response.ResponseObjectI;

public class Ellipse extends Shape implements ResponseObjectI {
    private int r1,r2;
    private Point center;

    public int getR1() {
        return r1;
    }

    public int getR2() {
        return r2;
    }

    public Point getCenter() {
        return center;
    }
}
