package com.example.demo.services;

import com.example.demo.Model.DrawnShapes;
import com.example.demo.Model.ShapesList;
import com.example.demo.response.ResponseObject;
import com.example.demo.shapes.Point;
import com.example.demo.shapes.Shape;
import com.example.demo.shapes.ShapeI;
import com.example.demo.Model.DrawnShapesI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public ShapesList getdrawns(){
        return this.drawnShapes.getDrawnShapes();
    }


    public boolean save(String path, String fileType) {
        return drawnShapes.saveDrawnShapes(path, fileType);
    }

    public List<ResponseObject> load(String path, String fileType) {
        drawnShapes.loadDrawnShapes(path, fileType);
        return drawnShapes.getResponses();
    }

    public List<ResponseObject> undo() {
        drawnShapes.undoShapes();
        return drawnShapes.getResponses();
    }

    public List<ResponseObject> redo() {
        drawnShapes.redoShape();
        return drawnShapes.getResponses();
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
