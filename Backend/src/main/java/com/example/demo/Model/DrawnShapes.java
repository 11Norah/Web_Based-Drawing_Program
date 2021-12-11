package com.example.demo.Model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.factory.ObjectFactoryI;
import com.example.demo.factory.ObjectFactoryService;
import com.example.demo.response.ResponseObject;
import com.example.demo.shapes.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class DrawnShapes implements DrawnShapesI {
    private ShapesList drawnShapes;
    private ObjectMapper mapper;
    private XmlMapper xmlMapper;
    private ResponsesList responses;
    private ResponsesList undoneResponses;


    public List<ResponseObject> getResponses(){
        return this.responses;
    }
    public ShapesList getDrawnShapes(){
        return this.drawnShapes;
    }

    public int checkCoordinate(Point click) {
        for (int i = drawnShapes.size() - 1; i >= 0; i--) {
            ShapeI temp = drawnShapes.get(i);
            if (temp.range(click)) {
                return i;
            }
        }
        return -1;
    }

    public DrawnShapes() {
        this.drawnShapes = new ShapesList();
        this.undoneResponses = new ResponsesList();
        this.responses = new ResponsesList();
        mapper = new ObjectMapper();
        xmlMapper = new XmlMapper();
    }

    public void addResponse(ResponseObject response) {
        this.responses.add(response);
    }


    public void undoShapes() {
        if (responses.size() != 0) {
            undoneResponses.add(responses.remove(responses.size() - 1));
            drawnShapes.remove(drawnShapes.size()-1);
        } else {
            System.out.println("Nothing to undo");
        }
    }
    public void clear(){
        drawnShapes.clear();
        undoneResponses.clear();
        responses.clear();
    }

    public void addShape(Shape shape) {
        drawnShapes.add(shape);
    }

    public void redoShape() {
        if (undoneResponses.size() != 0) {
            responses.add(undoneResponses.remove(undoneResponses.size() - 1));
            ObjectFactoryI factory = new ObjectFactoryService();
            ResponseObject res = responses.get(responses.size()-1);
            Point p1 = new Point(res.getX1(), res.getY1());
            Point p2 = new Point(res.getX2(), res.getY2());
            Point p3 = new Point(res.getX3(), res.getY3());
            Shape shape = factory.getObject(res.getName(), res.getColor(), p1, p2, p3);
            addShape(shape);
        } else {
            System.out.println("Nothing to redo");
        }
    }

    public ResponsesList loadDrawnShapes(String path, String fileType) {
        try {
            if(fileType.equals("json")) {
                responses = mapper.readValue(new File(path), ResponsesList.class);
                System.out.println("File loaded successfully");
                undoneResponses.clear();
            }
            else if(fileType.equals("xml")){
                responses = xmlMapper.readValue(new File(path), ResponsesList.class);
                System.out.println("File loaded successfully");
                undoneResponses.clear();
            }
            else {
                System.out.println("Unsupported type");
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
        ObjectFactoryI factory = new ObjectFactoryService();
        drawnShapes.clear();
        for(int i = 0; i < responses.size(); i++){
            ResponseObject res = responses.get(i);
            Point p1 = new Point(res.getX1(), res.getY1());
            Point p2 = new Point(res.getX2(), res.getY2());
            Point p3 = new Point(res.getX3(), res.getY3());
            Shape shape = factory.getObject(responses.get(i).getName(), responses.get(i).getColor(), p1, p2, p3);
            addShape(shape);
        }
        return responses;
    }

    public boolean saveDrawnShapes(String path, String fileType) {
        try {
            if (fileType.equals("json")) {
                mapper.writeValue(new File(path), responses);
                System.out.println("File saved successfully");
            } else if (fileType.equals("xml")) {
                xmlMapper.writeValue(new File(path), responses);
                System.out.println("File saved successfully");
            } else {
                System.out.println("Unsupported type");
            }
            return true;
        } catch (IOException exception) {
            System.out.println("Failed to save file");
            return false;
        }
    }


    public void move(int index,Point click) {
        Shape tempShape;
        ResponseObject tempResponse;
        drawnShapes.get(index).afterMove(click);
        tempShape = drawnShapes.get(index);
        drawnShapes.remove(index);
        drawnShapes.add(tempShape);
        double x1 = 0, x2 = 0, x3 = 0, y1 = 0, y2 = 0, y3 = 0;
        if(tempShape.getPoints()[0] != null) {
            x1 = tempShape.getPoints()[0].getX();
            y1 = tempShape.getPoints()[0].getY();
        }
        if(tempShape.getPoints()[1] != null) {
            x2 = tempShape.getPoints()[1].getX();
            y2 = tempShape.getPoints()[1].getY();
        }
        if(tempShape.getPoints()[2] != null) {
            x3 = tempShape.getPoints()[2].getX();
            y3 = tempShape.getPoints()[2].getY();
        }
        tempResponse = new ResponseObject(tempShape.getName(),tempShape.getColor(), x1, y1, x2, y2, x3, y3);
        responses.remove(index);
        responses.add(tempResponse);
    }

    public void copy(int index,Point click) {
        Shape originalShape,newShape;
        ResponseObject tempResponse;
        originalShape = drawnShapes.get(index);
        drawnShapes.get(index).afterMove(click);
        newShape = drawnShapes.get(index);
        drawnShapes.remove(index);
        drawnShapes.add(index,originalShape);
        drawnShapes.add(newShape);
        double x1 = 0, x2 = 0, x3 = 0, y1 = 0, y2 = 0, y3 = 0;
        if(newShape.getPoints()[0] != null) {
            x1 = newShape.getPoints()[0].getX();
            y1 = newShape.getPoints()[0].getY();
        }
        if(newShape.getPoints()[1] != null) {
            x2 = newShape.getPoints()[1].getX();
            y2 = newShape.getPoints()[1].getY();
        }
        if(newShape.getPoints()[2] != null) {
            x3 = newShape.getPoints()[2].getX();
            y3 = newShape.getPoints()[2].getY();
        }

        tempResponse = new ResponseObject(newShape.getName(), newShape.getColor(), x1, y1, x2, y2, x3, y3);
        responses.add(tempResponse);
    }

    public void delete(int index) {
        drawnShapes.remove(index);
        responses.remove(index);
    }
    public void resize(int index,Point p1,Point p2) {
        drawnShapes.get(index).resize(p1,p2);
    }

}