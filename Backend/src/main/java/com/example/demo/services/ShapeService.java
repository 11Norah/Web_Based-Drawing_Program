package com.example.demo.services;

import com.example.demo.Model.DrawnShapes;
import com.example.demo.shapes.Point;
import com.example.demo.shapes.Shape;
import com.example.demo.shapes.ShapeI;
import com.example.demo.Model.DrawnShapesI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShapeService {


    @Autowired
    DrawnShapes drawnShapes;

    public void add(Shape shape) {
        drawnShapes.addShape(shape);
    }
    public DrawnShapes getDrawnShapes(){
        return this.drawnShapes;
    }

    public void save(String path) {
        drawnShapes.saveDrawnShapes(path);
    }

    public int checkCoordinate(Point click) {
        return drawnShapes.checkCoordinate(click);
    }

    public void addResponse(String name, String color, double x1, double y1, double x2, double y2, double x3, double y3) {
        drawnShapes.addResponse(name, color, x1,y1,x2,y2,x3,y3);
    }
    public void clear(){
        drawnShapes.clear();
    }
    public void move(int index, Point click) {
        drawnShapes.move(index, click);
    }
    public void copy(int index, Point click) {
        drawnShapes.copy(index, click);
    }
    public void delete(int index) {
        drawnShapes.delete(index);
    }
    public void resize(int index,Point p1,Point p2) {
        drawnShapes.resize(index,p1,p2);
    }

}
