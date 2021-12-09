package com.example.demo.shapes;


public interface ShapeI {
    public String getColor();

    public String getName();

    public void setColor(String color);

    public void afterMove(Point click);

    public boolean range(Point click);

    public Point[] getPoints();
    public void resize(Point p1,Point p2);

}
