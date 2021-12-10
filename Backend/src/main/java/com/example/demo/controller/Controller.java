package com.example.demo.controller;

import com.example.demo.Model.DrawnShapes;
import com.example.demo.Model.DrawnShapesI;
import com.example.demo.factory.ObjectFactoryService;
import com.example.demo.response.ResponseObject;
import com.example.demo.services.ShapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.shapes.*;

import java.util.List;

@CrossOrigin
@RestController
public class Controller {

    @Autowired
    ShapeService serve;

    @Autowired
    ObjectFactoryService factory;


    @PostMapping("/add")
    public void integration(@RequestBody String name, @RequestBody String color, @RequestBody Point first, @RequestBody Point second, @RequestBody Point third) {
        serve.add(factory.getObject(name, color, first, second, third));
        serve.addResponse(name, color, first, second, third);
    }

    @PostMapping("/move")
    public List<ResponseObject> move(@RequestBody Point click, @RequestBody Point moveTo) {
        int index = serve.checkCoordinate(click);
        if (index != -1) {
            serve.move(index, moveTo);
            return serve.getDrawnShapes().getResponses();
        }
        return null;
    }

    @PostMapping("/copy")
    public List<ResponseObject> copy(@RequestBody Point click, @RequestBody Point copyTo) {
        int index = serve.checkCoordinate(click);
        if (index != -1) {
            serve.copy(index, copyTo);
            return serve.getDrawnShapes().getResponses();
        }
        return null;
    }

    @PostMapping("/delete")
    public List<ResponseObject> delete(@RequestBody Point click) {
        int index = serve.checkCoordinate(click);
        if (index != -1) {
            serve.delete(index);
            return serve.getDrawnShapes().getResponses();
        }
        return null;
    }

    @PostMapping("/resize")
    public List<ResponseObject> resize(@RequestBody Point click, @RequestBody Point p1, @RequestBody Point p2) {
        int index = serve.checkCoordinate(click);
        if (index != -1) {
            serve.resize(index, p1, p2);
            return serve.getDrawnShapes().getResponses();
        }
        return null;
    }
    @PostMapping("/clear")
    public void clear() {
        serve.clear();
    }

}
