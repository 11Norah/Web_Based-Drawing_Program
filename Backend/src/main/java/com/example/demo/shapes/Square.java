package com.example.demo.shapes;

import com.example.demo.response.ResponseObjectI;

public class Square extends Shape implements ResponseObjectI {
    private Point topLeft,bottomRight;
    int length;

    public void evalLength(){
        this.length = Math.abs(topLeft.getX()-bottomRight.getX());
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
