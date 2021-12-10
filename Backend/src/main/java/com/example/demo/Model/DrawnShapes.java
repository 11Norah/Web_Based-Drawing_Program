package com.example.demo.Model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.response.ResponseObject;
import com.example.demo.shapes.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Component;

@Component
public class DrawnShapes implements DrawnShapesI {
    private ShapesList drawnShapes;
    private ShapesList undoneShapes;
    private ObjectMapper mapper;
    private XmlMapper xmlMapper = new XmlMapper();
    private List<ResponseObject> responses;


    public List<ResponseObject> getResponses(){
        return this.responses;
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
        this.undoneShapes = new ShapesList();
        this.responses = new ArrayList<>();
        mapper = new ObjectMapper();
    }

    public void addResponse(String name, String color, double x1, double y1, double x2, double y2, double x3, double y3) {
        ResponseObject response = new ResponseObject(name, color, x1,y1,x2,y2,x3,y3);
        this.responses.add(response);
    }


    public void undoShapes() {
        if (drawnShapes.size() != 0) {
            undoneShapes.add(drawnShapes.remove(drawnShapes.size() - 1));
            responses.remove(responses.size()-1);
        } else {
            System.out.println("Nothing to undo");
        }
    }
    public void clear(){
        drawnShapes.clear();
        undoneShapes.clear();
        responses.clear();
    }

    public void addShape(Shape shape) {
        drawnShapes.add(shape);
    }

    public void redoShape() {
        if (undoneShapes.size() != 0) {
            drawnShapes.add(undoneShapes.remove(drawnShapes.size() - 1));
            Shape shape = drawnShapes.get(drawnShapes.size()-1);
            String name = shape.getName();
            String color = shape.getColor();
            double x1 = shape.getPoints()[0].getX();
            double y1 = shape.getPoints()[0].getY();
            double x2 = shape.getPoints()[1].getX();
            double y2 = shape.getPoints()[1].getY();
            double x3 = shape.getPoints()[2].getX();
            double y3 = shape.getPoints()[2].getY();
            addResponse(name, color, x1, y1, x2, y2, x3, y3);
        } else {
            System.out.println("Nothing to redo");
        }
    }

    public void loadDrawnShapes(String path, String fileType) {
        try {
            if(fileType.equals("json")) {
                drawnShapes = mapper.readValue(new File(path), ShapesList.class);
                System.out.println("File loaded successfully");
                undoneShapes.clear();
            }
            else if(fileType.equals("xml")){
                drawnShapes = xmlMapper.readValue(new File(path), ShapesList.class);
                System.out.println("File loaded successfully");
                undoneShapes.clear();
            }
            else {
                System.out.println("Unsupported type");
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
        for(int i = 0; i < drawnShapes.size(); i++){
            String name = drawnShapes.get(i).getName();
            String color = drawnShapes.get(i).getColor();
            double x1 = drawnShapes.get(i).getPoints()[0].getX();
            double y1 = drawnShapes.get(i).getPoints()[0].getY();
            double x2 = drawnShapes.get(i).getPoints()[1].getX();
            double y2 = drawnShapes.get(i).getPoints()[1].getY();
            double x3 = drawnShapes.get(i).getPoints()[2].getX();
            double y3 = drawnShapes.get(i).getPoints()[2].getY();
            addResponse(name, color, x1, y1, x2, y2, x3, y3);
        }
    }

    public boolean saveDrawnShapes(String path, String fileType) {
        try {
            if (fileType.equals("json")) {
                mapper.writeValue(new File(path), drawnShapes);
                System.out.println("File saved successfully");
            } else if (fileType.equals("xml")) {
                xmlMapper.writeValue(new File(path), drawnShapes);
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
        tempResponse = new ResponseObject(tempShape.getName(),tempShape.getColor(),tempShape.getPoints()[0].getX(),tempShape.getPoints()[0].getY(),tempShape.getPoints()[1].getX(),tempShape.getPoints()[1].getY(),tempShape.getPoints()[2].getX(),tempShape.getPoints()[2].getY() );
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
        tempResponse = new ResponseObject(newShape.getName(),newShape.getColor(),newShape.getPoints()[0].getX(),newShape.getPoints()[0].getY(),newShape.getPoints()[1].getX(),newShape.getPoints()[1].getY(),newShape.getPoints()[2].getX(),newShape.getPoints()[2].getY() );
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