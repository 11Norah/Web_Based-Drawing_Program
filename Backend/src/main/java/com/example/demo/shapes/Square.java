package com.example.demo.shapes;

import com.example.demo.response.ResponseObjectI;

import java.awt.*;

public class Square extends Shape  {
    private Point topLeft,bottomRight;
    private int length;

    public Square(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.length = Math.abs(topLeft.x-bottomRight.x);
    }

    public int getLength() {
        return length;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(Point bottomRight) {
        this.bottomRight = bottomRight;
    }



}
