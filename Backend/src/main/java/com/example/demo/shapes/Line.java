package com.example.demo.shapes;


import com.example.demo.services.LineServices;
import org.springframework.beans.factory.annotation.Autowired;

public class Line extends Shape implements ShapeI {
    private Point p1, p2;
    private double length, m, c;
    private int type;


    LineServices lineServices = new LineServices();

    public Line(Point p1, Point p2, String color) {
        this.p1 = p1;
        this.p2 = p2;
        this.name = "line";
        this.color = color;
        constructLine();
    }


    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    @Override
    public void afterMove(Point click) {
        double x1, x2, y1, y2, cDash, a, b;
        this.p1 = click;
        this.c = click.getY() - this.m * click.getX();
        a = Math.pow(m, 2) + 1;
        b = 2 * m * c - 2 * m * click.getY() - 2 * click.getX();
        cDash = c * c - 2 * c * click.getY() - length * length + Math.pow(click.getY(), 2) + Math.pow(click.getX(), 2);
        double sqr00t = Math.sqrt(b * b - 4 * a * cDash);
        x1 = (-b + sqr00t) / 2 * a;
        x2 = (-b - sqr00t) / 2 * a;
        y1 = m * x1 + c;
        y2 = m * x2 + c;
        changeP2(x1, x2, y1, y2);
    }

    private void changeP2(double x1, double x2, double y1, double y2) {
        switch (this.type) {
            case 1:
                setP2(new Point(Math.max(x1, x2), Math.max(y1, y2)));
                break;
            case 2:
                setP2(new Point(Math.max(x1, x2), Math.min(y1, y2)));
                break;
            case 3:
                setP2(new Point(Math.min(x1, x2), Math.min(y1, y2)));
                break;
            case 4:
                setP2(new Point(Math.min(x1, x2), Math.max(y1, y2)));
                break;
            default:
                return;
        }
    }

    public boolean range(Point click) {
        double y = m * click.getX() + c;
        double dist1 = Math.pow((p1.getX() - click.getX()), 2) + Math.pow((p1.getY() - click.getY()), 2);
        double dist2 = Math.pow((p2.getX() - click.getX()), 2) + Math.pow((p2.getY() - click.getY()), 2);
        if (Math.sqrt(dist1) <= length && Math.sqrt(dist2) <= length && Math.abs(click.getY() - y) <= 2) {
            return true;
        }
        return false;
    }

    @Override
    public Point[] getPoints() {
        Point[] arr = new Point[3];
        arr[0] = p1;
        arr[1] = p2;
        arr[2] = null;
        return arr;
    }
    private void constructLine(){
        this.length = lineServices.getLength(this.p1,this.p2);
        this.c = lineServices.getC(this.p1,this.p2);
        this.m = lineServices.getM(this.p1,this.p2,this.c);
        this.type = lineServices.detectType(this.p1,this.p2);
    }

    @Override
    public void resize(Point p1, Point p2) {
        this.p2 = p1;
        constructLine();
    }
}
