package com.example.demo.shapes;

import com.example.demo.response.ResponseObjectI;

import java.awt.*;

public class Rectangle extends Shape  {
    private int width,height;
    private Point topLeft,bottomRight;

    public Rectangle(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.width = Math.abs(topLeft.y-bottomRight.y);
        this.height = Math.abs(topLeft.x-bottomRight.x);
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    public void setBottomRight(Point bottomRight) {
        this.bottomRight = bottomRight;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
