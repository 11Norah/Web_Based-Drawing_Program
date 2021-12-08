package com.example.demo.shapes;

import com.example.demo.response.ResponseObjectI;

public class Rectangle extends Shape implements ResponseObjectI {
    private int width,height;
    private Point topLeft,bottomRight;

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public void evalWidth(){
        this.width = Math.abs(topLeft.getY()-bottomRight.getY());
    }
    public void evalLength(){
        this.height = Math.abs(topLeft.getX()-bottomRight.getX());
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
