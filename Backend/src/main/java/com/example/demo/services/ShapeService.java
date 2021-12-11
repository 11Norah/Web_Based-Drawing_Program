package com.example.demo.services;

import com.example.demo.Model.DrawnShapes;
import com.example.demo.Model.ResponsesList;
import com.example.demo.Model.ShapesList;
import com.example.demo.factory.ObjectFactoryI;
import com.example.demo.factory.ObjectFactoryService;
import com.example.demo.response.ResponseObject;
import com.example.demo.services.undoServices.UndoObject;
import com.example.demo.shapes.Point;
import com.example.demo.shapes.Shape;
import com.example.demo.shapes.ShapeI;
import com.example.demo.Model.DrawnShapesI;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShapeService {


    @Autowired
    DrawnShapes drawnShapes;


    public void add(ResponseObject res) {
        drawnShapes.addResponse(res);
        ObjectFactoryI factory = new ObjectFactoryService();
        Point p1 = new Point(res.getX1(), res.getY1());
        Point p2 = new Point(res.getX2(), res.getY2());
        Point p3 = new Point(res.getX3(), res.getY3());
        Shape shape = factory.getObject(res.getName(), res.getColor(), p1, p2, p3);
        drawnShapes.addShape(shape);
    }

    public DrawnShapes getDrawnShapes() {
        return this.drawnShapes;
    }

    public ShapesList getdrawns() {
        return this.drawnShapes.getDrawnShapes();
    }


    public boolean save(String path, String fileType) {
        return drawnShapes.saveDrawnShapes(path, fileType);
    }

    public ResponsesList load(String path, String fileType) {
        return drawnShapes.loadDrawnShapes(path, fileType);
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

    public void clear() {
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

    public void resize(int index, Point p1, Point p2) {
        drawnShapes.resize(index, p1, p2);
    }

}
