package com.example.demo.services;

import com.example.demo.shapes.Point;
import org.springframework.stereotype.Component;

@Component
public class LineServices {

    public boolean checkPoint(Point p1, Point p2,Point click){
        double c = (p1.getX() * p2.getY() - p1.getY() * p2.getX()) / (p1.getX() - p2.getX());
        double m = (p1.getY() - c) / p1.getX();
        double length = Math.sqrt((Math.pow((p1.getX() - p2.getX()), 2) + Math.pow((p1.getY() - p2.getY()), 2)));
        double y = m * click.getX() + c;
        double dist1 = Math.pow((p1.getX() - click.getX()), 2) + Math.pow((p1.getY() - click.getY()), 2);
        double dist2 = Math.pow((p2.getX() - click.getX()), 2) + Math.pow((p2.getY() - click.getY()), 2);
        if (dist1 <= length && dist2 <= length && click.getY() == y) {
            return true;
        }
        return false;

    }
}
