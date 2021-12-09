package com.example.demo.Model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.demo.response.ResponseObject;
import com.example.demo.shapes.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.springframework.stereotype.Component;

@Component
public class DrawnShapes implements DrawnShapesI {
    private List<ShapeI> drawnShapes;
    private List<ShapeI> undoneShapes;
    private ObjectMapper mapper;
    private List<ResponseObject> responses;


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
        this.drawnShapes = new ArrayList<>();
        this.undoneShapes = new ArrayList<>();
        this.responses = new ArrayList<>();
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public void addResponse(String name, String color, Point first, Point second, Point third) {
        ResponseObject response = new ResponseObject(name, color, first, second, third);
        this.responses.add(response);
    }

    public void undoShapes() {
        if (drawnShapes.size() != 0) {
            undoneShapes.add(drawnShapes.remove(drawnShapes.size() - 1));
        } else {
            System.out.println("Nothing to undo");
        }
    }

    public void addShape(ShapeI shape) {
        drawnShapes.add(shape);
    }

    public void redoShape() {
        if (undoneShapes.size() != 0) {
            drawnShapes.add(undoneShapes.remove(drawnShapes.size() - 1));
        } else {
            System.out.println("Nothing to redo");
        }
    }

    public JSONArray loadDrawnShapes(String path) {
        try {
            ShapeI[] shapes = mapper.readValue(new File(path), Shape[].class);
            System.out.println("File loaded successfully");
            undoneShapes.clear();
            drawnShapes = Arrays.asList(shapes);
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return new JSONArray(drawnShapes);
    }

    public boolean saveDrawnShapes(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(path), drawnShapes);
        } catch (IOException exception) {
            return false;
        }
        return true;
    }

    public void move(int index,Point click) {
        ShapeI tempShape;
        ResponseObject tempResponse;
        drawnShapes.get(index).afterMove(click);
        tempShape = drawnShapes.get(index);
        drawnShapes.remove(index);
        drawnShapes.add(tempShape);
        tempResponse = new ResponseObject(tempShape.getName(),tempShape.getColor(),tempShape.getPoints()[0],tempShape.getPoints()[1],tempShape.getPoints()[2] );
        responses.remove(index);
        responses.add(tempResponse);
    }

    public void copy(int index,Point click) {
        ShapeI originalShape,newShape;
        ResponseObject tempResponse;
        originalShape = drawnShapes.get(index);
        drawnShapes.get(index).afterMove(click);
        newShape = drawnShapes.get(index);
        drawnShapes.remove(index);
        drawnShapes.add(index,originalShape);
        drawnShapes.add(newShape);
        tempResponse = new ResponseObject(newShape.getName(),newShape.getColor(),newShape.getPoints()[0],newShape.getPoints()[1],newShape.getPoints()[2] );
        responses.add(tempResponse);
    }

    public void delete(int index) {
        drawnShapes.remove(index);
        responses.remove(index);
    }

}